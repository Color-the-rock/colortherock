package org.anotherclass.colortherock.domain.live.service;

import io.openvidu.java.client.*;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.live.exception.RecordingStartBadRequestException;
import org.anotherclass.colortherock.domain.live.exception.SessionNotFountException;
import org.anotherclass.colortherock.domain.live.repository.LiveRepository;
import org.anotherclass.colortherock.domain.live.request.CreateLiveRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingStartRequest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LiveService {

    private final LiveRepository liveRepository;
    private final MemberRepository memberRepository;

    private final OpenVidu openVidu;

    public LiveService(LiveRepository liveRepository,
                       MemberRepository memberRepository,
                       @Value("${OPENVIDU_URL}") String OPENVIDU_URL,
                       @Value("${OPENVIDU_SECRET}") String OPENVIDU_SECRET) {
        this.liveRepository = liveRepository;
        this.memberRepository = memberRepository;
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    public String createLiveRoom(MemberDetails memberDetails, CreateLiveRequest request) {
        Long id = memberDetails.getMember().getId();
        Member member = memberRepository.findById(id).orElseThrow();
        Session session;
        // TODO 어떤 오류가 나는지 불명
        try {
            session = openVidu.createSession();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
        String sessionId = session.getSessionId();

        // TODO 섬네일 어떻게 받을지?
        Live live = request.toEntity(sessionId, "대충 섬네일", member);
        Live save = liveRepository.save(live);
        try {
            Connection connection = session.createConnection(new ConnectionProperties.Builder().role(OpenViduRole.PUBLISHER).build());
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    public String joinLiveRoom(MemberDetails memberDetails, String sessionId) {
        Long userId = memberDetails.getMember().getId();
        Session activeSession = openVidu.getActiveSession(sessionId);
        if (activeSession == null) {
            throw new SessionNotFountException();
        }
        try {
            Connection connection = activeSession.createConnection(new ConnectionProperties.Builder().role(OpenViduRole.SUBSCRIBER).build());
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    public void recordingStart(MemberDetails memberDetails, String sessionId, RecordingStartRequest request) {

        Session activeSession = openVidu.getActiveSession(sessionId);
        if (activeSession == null) {
            throw new SessionNotFountException();
        }
        String token = request.getToken();
        Connection connection = activeSession.getConnection(token);
        OpenViduRole role = connection.getRole();

        if (role.equals(OpenViduRole.PUBLISHER)) {
            try {
                openVidu.startRecording(sessionId);
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RecordingStartBadRequestException();
    }
}

package org.anotherclass.colortherock.domain.live.service;

import io.openvidu.java.client.*;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.live.exception.RecordingStartBadRequestException;
import org.anotherclass.colortherock.domain.live.exception.SessionNotFountException;
import org.anotherclass.colortherock.domain.live.repository.LiveRepository;
import org.anotherclass.colortherock.domain.live.request.CreateLiveRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingSaveRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingStartRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingStopRequest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.service.S3Service;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LiveService {

    private final S3Service s3Service;
    private final LiveRepository liveRepository;
    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;

    private final OpenVidu openVidu;

    @Value("${RECORDING_PATH}") String dir;

    public LiveService(LiveRepository liveRepository,
                       MemberRepository memberRepository,
                       VideoRepository videoRepository,
                       S3Service s3Service,
                       @Value("${OPENVIDU_URL}") String OPENVIDU_URL,
                       @Value("${OPENVIDU_SECRET}") String OPENVIDU_SECRET) {
        this.s3Service = s3Service;
        this.liveRepository = liveRepository;
        this.memberRepository = memberRepository;
        this.videoRepository = videoRepository;
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
        liveRepository.save(live);
        try {
            Connection connection = session.createConnection(new ConnectionProperties.Builder().role(OpenViduRole.PUBLISHER).build());
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    public String joinLiveRoom(String sessionId) {
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

    public String recordingStart(String sessionId, RecordingStartRequest request) {

        Session activeSession = openVidu.getActiveSession(sessionId);
        if (activeSession == null) {
            throw new SessionNotFountException();
        }
        String token = request.getToken();
        Connection connection = activeSession.getConnection(token);
        OpenViduRole role = connection.getRole();

        if (role.equals(OpenViduRole.PUBLISHER)) {
            try {
                Recording recording = openVidu.startRecording(sessionId);
                return recording.getId();
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RecordingStartBadRequestException();
    }

    public void recordingStop(RecordingStopRequest request) {

        try {
            openVidu.stopRecording(request.getRecordingId());
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
        throw new RecordingStartBadRequestException();
    }

    public void recordingSave(MemberDetails memberDetails, String sessionId, RecordingSaveRequest request) throws IOException {
        dir += "/" + request.getRecordingId() + "/" + request.getRecordingId() + ".webm";
        String videoName = DateTime.now() + request.getRecordingId();
        String s3Url = s3Service.uploadFromLocal(dir, videoName);
        Member member = memberRepository.findById(memberDetails.getMember().getId()).orElseThrow();
        Video video = request.toEntity(s3Url, "섬네일 url", member);
        videoRepository.save(video);
    }
}

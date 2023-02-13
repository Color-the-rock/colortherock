package org.anotherclass.colortherock.domain.live.service;

import io.openvidu.java.client.*;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.live.entity.Live;
import org.anotherclass.colortherock.domain.live.exception.RecordingDeleteException;
import org.anotherclass.colortherock.domain.live.exception.RecordingStartBadRequestException;
import org.anotherclass.colortherock.domain.live.exception.SessionNotFountException;
import org.anotherclass.colortherock.domain.live.repository.LiveReadRepository;
import org.anotherclass.colortherock.domain.live.repository.LiveRepository;
import org.anotherclass.colortherock.domain.live.request.*;
import org.anotherclass.colortherock.domain.live.response.LiveListResponse;
import org.anotherclass.colortherock.domain.live.response.PrevRecordingListResponse;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.exception.MemberNotFoundException;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.exception.UserNotFoundException;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.anotherclass.colortherock.domain.video.service.S3Service;
import org.anotherclass.colortherock.domain.video.service.VideoService;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LiveService {

    private final S3Service s3Service;
    private final VideoService videoService;
    private final RecordService recordService;
    private final LiveRepository liveRepository;
    private final LiveReadRepository liveReadRepository;
    private final MemberRepository memberRepository;
    private static ConcurrentMap<String, List<String>> recordingsForSession = new ConcurrentHashMap<>();
    private final OpenVidu openVidu;
    private static final Integer PAGE_SIZE = 15;


    @Value("${RECORDING_PATH}")
    private final String recordingPath;

    public LiveService(LiveRepository liveRepository,
                       MemberRepository memberRepository,
                       S3Service s3Service,
                       VideoService videoService,
                       RecordService recordService,
                       LiveReadRepository liveReadRepository, @Value("${OPENVIDU_URL}") String openviduUrl,
                       @Value("${OPENVIDU_SECRET}") String openviduSecret,
                       final @Value("${RECORDING_PATH}") String recordingPath) {
        this.s3Service = s3Service;
        this.videoService = videoService;
        this.recordService = recordService;
        this.liveRepository = liveRepository;
        this.memberRepository = memberRepository;
        this.liveReadRepository = liveReadRepository;
        this.openVidu = new OpenVidu(openviduUrl, openviduSecret);
        this.recordingPath = recordingPath;
    }

    public String createLiveRoom(MemberDetails memberDetails, CreateLiveRequest request, MultipartFile thumbnail) {
        Long id = memberDetails.getMember().getId();
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Session session;
        try {
            session = openVidu.createSession();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
        String sessionId = session.getSessionId();
        String thumbnailName = System.currentTimeMillis() + sessionId;
        String uploadedURL;
        try {
            uploadedURL = s3Service.upload(thumbnail, thumbnailName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Live live = request.toEntity(sessionId, member, uploadedURL, thumbnailName);
        liveRepository.save(live);
        try {
            Connection connection = session.createConnection(new ConnectionProperties.Builder().role(OpenViduRole.PUBLISHER).build());
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    public String joinLiveRoom(String sessionId) {
        try {
            openVidu.fetch();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
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
        try {
            openVidu.fetch();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
        Session activeSession = openVidu.getActiveSession(sessionId);
        if (activeSession == null) {
            throw new SessionNotFountException();
        }
        String connectionId = request.getConnectionId();
        Connection connection = activeSession.getConnection(connectionId);
        OpenViduRole role = connection.getRole();

        if (role.equals(OpenViduRole.PUBLISHER)) {
            try {
                Recording recording = openVidu.startRecording(sessionId);
                String recordingId = recording.getId();
                List<String> recordings = recordingsForSession.getOrDefault(sessionId, new ArrayList<>());
                recordings.add(recordingId);
                recordingsForSession.put(sessionId, recordings);
                return recordingId;
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
    }

    @Transactional(readOnly = true)
    public List<LiveListResponse> getLiveList(LiveListRequest liveListRequest) {
        Pageable pageable = Pageable.ofSize(PAGE_SIZE);

        Slice<Live> slices = liveReadRepository.searchBySlice(liveListRequest, pageable);

        if (slices.isEmpty()) return new ArrayList<>();

        // list를 받아와서 openvidu의 active session과 비교하여 없으면 DB 삭제하는 방식으로 DB를 최적화
        List<String> activeSessions = openVidu.getActiveSessions().stream().map(Session::getSessionId).collect(Collectors.toList());

        List<LiveListResponse> responses = new ArrayList<>();

        slices.toList().forEach(live -> {
            if (activeSessions.contains(live.getSessionId())) {
                responses.add(LiveListResponse.builder()
                        .id(live.getId())
                        .title(live.getTitle())
                        .memberId(live.getMember().getId())
                        .memberName(live.getMember().getNickname())
                        .gymName(live.getGymName())
                        .sessionId(live.getSessionId())
                        .participantNum(
                                openVidu.getActiveSession(live.getSessionId()).getActiveConnections().size()
                        )
                        .thumbnailUrl(live.getThumbnailURL()).build());
            } else {
                liveRepository.delete(live);
            }
        });
        return responses;
    }

    public List<PrevRecordingListResponse> getRecordings(String sessionId) {
        List<String> recordingIds = recordingsForSession.get(sessionId);
        List<PrevRecordingListResponse> response = new ArrayList<>();
        recordingIds.forEach(recordingId -> {
            try {
                Recording recording = openVidu.getRecording(recordingId);
                if (recording.getStatus() == Recording.Status.ready) {
                    response.add(new PrevRecordingListResponse(recording));
                }
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        });
        return response;
    }

    public void deleteRecording(String sessionId, String recordingId) {
        recordingsForSession.get(sessionId).remove(recordingId);
        try {
            openVidu.deleteRecording(recordingId);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RecordingDeleteException();
        }
    }

    @Transactional
    public void removeSession(String sessionId) {
        Optional<Live> live = liveRepository.findBySessionId(sessionId);
        live.ifPresent(value -> s3Service.deleteFile(value.getThumbnailName()));
        liveRepository.deleteBySessionId(sessionId);
    }

    public void recordingSave(MemberDetails memberDetails, RecordingSaveRequest request) {

        WebClient webClient = WebClient.create();
        webClient
                .post()
                .uri("https://colortherock.com/api/live/uploadRecord")
                .bodyValue(new RecordingUploadAtOpenviduServerRequest(request, memberDetails.getMember().getId()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Transactional
    public void uplooadAtOpenviduServer(RecordingUploadAtOpenviduServerRequest request) throws IOException, JCodecException {
        String newDir = recordingPath + "/" + request.getRecordingId() + "/" + request.getRecordingId() + ".mp4";
        String videoName = System.currentTimeMillis() + request.getRecordingId() + ".mp4";
        String s3Url = s3Service.uploadFromOV(newDir, videoName);
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> {
            throw new MemberNotFoundException(GlobalErrorCode.USER_NOT_FOUND);
        });
        // 썸네일 추가
        String thumbnailName = "Thumb" + System.currentTimeMillis() + request.getRecordingId() + ".JPEG";
        File file = new File(recordingPath + "/" + request.getRecordingId());
        File[] files = file.listFiles();
        for (File file1 : files) {
            log.info(file1.getAbsolutePath());
        }
        String thumbnailURL = s3Service.uploadThumbnailFromOV(newDir, thumbnailName);
        file = new File(recordingPath + "/" + request.getRecordingId());
        files = file.listFiles();
        for (File file1 : files) {
            log.info(file1.getAbsolutePath());
        }
        // 비디오 저장
        UploadVideoRequest uploadVideoRequest = UploadVideoRequest.builder()
                .shootingDate(LocalDate.now())
                .level(request.getLevel())
                .isSuccess(request.getIsSuccess())
                .color(request.getColor())
                .gymName(request.getGymName()).build();

        videoService.uploadVideo(member, s3Url, thumbnailURL, thumbnailName, uploadVideoRequest, videoName);
        file = new File(recordingPath + "/" + request.getRecordingId());
        files = file.listFiles();
        for (File file1 : files) {
            log.info(file1.getAbsolutePath());
        }
        // 영상 누적 통계에서 영상 갯수 올리기
        recordService.addVideoCount(member, uploadVideoRequest.getIsSuccess());
    }
}

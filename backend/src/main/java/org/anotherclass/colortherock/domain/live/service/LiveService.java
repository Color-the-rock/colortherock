package org.anotherclass.colortherock.domain.live.service;

import io.openvidu.java.client.*;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.exception.OpenviduException;
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
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.service.S3Service;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

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

    private final RecordService recordService;
    private final LiveRepository liveRepository;
    private final LiveReadRepository liveReadRepository;
    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;
    private static final ConcurrentMap<String, List<String>> recordingsForSession = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, String> urlsForRecordings = new ConcurrentHashMap<>();
    private final OpenVidu openVidu;
    private static final Integer PAGE_SIZE = 15;


    @Value("${RECORDING_PATH}")
    private final String recordingPath;

    public LiveService(LiveRepository liveRepository,
                       MemberRepository memberRepository,
                       S3Service s3Service,
                       RecordService recordService,
                       VideoRepository videoRepository,
                       LiveReadRepository liveReadRepository, @Value("${OPENVIDU_URL}") String openviduUrl,
                       @Value("${OPENVIDU_SECRET}") String openviduSecret,
                       final @Value("${RECORDING_PATH}") String recordingPath) {
        this.s3Service = s3Service;
        this.recordService = recordService;
        this.liveRepository = liveRepository;
        this.memberRepository = memberRepository;
        this.liveReadRepository = liveReadRepository;
        this.videoRepository = videoRepository;
        this.openVidu = new OpenVidu(openviduUrl, openviduSecret);
        this.recordingPath = recordingPath;
    }

    /**
     * ????????? ?????? ?????????.
     *
     * @param memberDetails ????????? ??????
     * @param request       {@link CreateLiveRequest} ????????? ??? ?????? ??????
     * @param thumbnail     ?????????
     * @return ???????????? ????????? ????????? ??????
     */
    public String createLiveRoom(MemberDetails memberDetails, CreateLiveRequest request, MultipartFile thumbnail) {
        Long id = memberDetails.getMember().getId();
        Member member = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Session session;
        try {
            session = openVidu.createSession();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new OpenviduException(e);
        }
        String thumbnailName = System.currentTimeMillis() + session.getSessionId();
        String uploadedURL;
        uploadedURL = s3Service.upload(thumbnail, thumbnailName);
        Live live = request.toEntity(session.getSessionId(), member, uploadedURL, thumbnailName);
        liveRepository.save(live);
        try {
            Connection connection = session.createConnection(new ConnectionProperties.Builder().role(OpenViduRole.PUBLISHER).build());
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new OpenviduException(e);
        }
    }

    /**
     * ????????? ?????? ??????
     *
     * @param sessionId ?????? id
     * @return ????????? token
     */
    public String joinLiveRoom(String sessionId) {
        try {
            openVidu.fetch();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new OpenviduException(e);
        }
        Session activeSession = openVidu.getActiveSession(sessionId);
        if (activeSession == null) {
            throw new SessionNotFountException();
        }
        try {
            Connection connection = activeSession.createConnection(new ConnectionProperties.Builder().role(OpenViduRole.SUBSCRIBER).build());
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new OpenviduException(e);
        }
    }

    /**
     * ?????? ?????? ??????
     *
     * @param sessionId ??? ?????? id
     * @param request   ?????? ?????? {@link RecordingStartRequest}
     * @return recording id ??????
     */

    public String recordingStart(String sessionId, RecordingStartRequest request) {
        try {
            openVidu.fetch();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new OpenviduException(e);
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
                RecordingProperties properties = new RecordingProperties.Builder()
                        .resolution("720x1280")
                        .frameRate(50).build();
                Recording recording = openVidu.startRecording(sessionId, properties);
                String recordingId = recording.getId();
                List<String> recordings = recordingsForSession.getOrDefault(sessionId, new ArrayList<>());
                recordings.add(recordingId);
                recordingsForSession.put(sessionId, recordings);
                return recordingId;
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new OpenviduException(e);
            }
        }
        throw new RecordingStartBadRequestException();
    }

    /**
     * ?????? ?????? ??????
     *
     * @param request ?????? ?????? ?????? {@link RecordingStopRequest}
     */
    public void recordingStop(RecordingStopRequest request) {

        try {
            openVidu.stopRecording(request.getRecordingId());
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new OpenviduException(e);
        }
    }

    /**
     * ????????? ??? ?????? ??????
     *
     * @param liveListRequest {@link LiveListRequest}
     * @return {@link LiveListResponse} ????????? ????????? ??????
     */
    @Transactional(readOnly = true)
    public List<LiveListResponse> getLiveList(LiveListRequest liveListRequest) {
        Pageable pageable = Pageable.ofSize(PAGE_SIZE);

        Slice<Live> slices = liveReadRepository.searchBySlice(liveListRequest, pageable);

        if (slices.isEmpty()) return new ArrayList<>();

        // list??? ???????????? openvidu??? active session??? ???????????? ????????? DB ???????????? ???????????? DB??? ?????????
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

    /**
     * ?????? ?????? ????????? ???????????? ??????
     *
     * @param sessionId ?????? id
     * @return {@link PrevRecordingListResponse} ????????? ????????? ??????
     */
    public List<PrevRecordingListResponse> getRecordings(String sessionId) {
        List<String> recordingIds = recordingsForSession.get(sessionId);
        List<PrevRecordingListResponse> response = new ArrayList<>();
        recordingIds.forEach(recordingId -> {
            try {
                Recording recording = openVidu.getRecording(recordingId);
                if (recording.getStatus() == Recording.Status.ready) {
                    PrevRecordingListResponse recordingListResponse = new PrevRecordingListResponse(recording);
                    recordingListResponse.setUrl(urlsForRecordings.get(recordingId));
                    response.add(recordingListResponse);
                }
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new OpenviduException(e);
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

    /**
     * ??? ?????? ??????
     *
     * @param sessionId ??? session Id
     */
    @Transactional
    public void removeSession(String sessionId) {
        Optional<Live> live = liveRepository.findBySessionId(sessionId);
        live.ifPresent(value -> s3Service.deleteFile(value.getThumbnailName()));
        liveRepository.deleteBySessionId(sessionId);
    }

    /**
     * ?????? ?????? ??????
     *
     * @param memberDetails ????????? ?????????
     * @param request       {@link RecordingSaveRequest} ?????? ?????? ??????
     */
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

    /**
     * ???????????? ???????????? ?????? ????????? ????????? ?????? ??????
     * ???????????? ?????? ?????? ?????????????????? ???????????? ????????? ????????????.
     *
     * @param request {@link RecordingUploadAtOpenviduServerRequest} ???????????? ?????? ????????? ??????
     */
    @Transactional
    public void uploadAtOpenviduServer(RecordingUploadAtOpenviduServerRequest request) {
        String videoExtension = ".mp4";
        String newDir = recordingPath + "/" + request.getRecordingId() + "/" + request.getRecordingId() + videoExtension;
        String videoName = System.currentTimeMillis() + request.getRecordingId() + videoExtension;
        String s3Url = s3Service.uploadFromOV(newDir, videoName);
        urlsForRecordings.put(request.getRecordingId(), s3Url);
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> {
            throw new MemberNotFoundException(GlobalErrorCode.USER_NOT_FOUND);
        });
        // ????????? ??????
        String thumbnailName = "Thumb" + System.currentTimeMillis() + request.getRecordingId() + ".JPEG";
        String thumbnailURL = s3Service.uploadThumbnailFromOV(newDir, thumbnailName);
        // ????????? ??????
        videoRepository.save(Video.builder()
                .shootingDate(LocalDate.now())
                .level(request.getLevel())
                .gymName(request.getGymName())
                .s3URL(s3Url)
                .videoName(videoName)
                .isSuccess(request.getIsSuccess())
                .thumbnailURL(thumbnailURL)
                .thumbnailName(thumbnailName)
                .color(request.getColor())
                .isPosted(false)
                .member(member)
                .build());
        // ?????? ?????? ???????????? ?????? ?????? ?????????
        recordService.addVideoCount(member, request.getIsSuccess());
    }
}

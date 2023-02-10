package org.anotherclass.colortherock.domain.live.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.live.request.*;
import org.anotherclass.colortherock.domain.live.response.LiveListResponse;
import org.anotherclass.colortherock.domain.live.response.PrevRecordingListResponse;
import org.anotherclass.colortherock.domain.live.service.LiveService;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.jcodec.api.JCodecException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class LiveController {

    private final LiveService liveService;

    @Operation(description = "진행중인 라이브 목록 반환 API", summary = "진행중인 라이브 목록 반환 API")
    @ApiResponse(responseCode = "200", description = "목록 반환 성공", content = @Content(schema = @Schema(implementation = LiveListResponse.class)))
    @GetMapping("/live/list")
    public BaseResponse<List<LiveListResponse>> getLiveList(LiveListRequest liveListRequest) {
        List<LiveListResponse> liveList = liveService.getLiveList(liveListRequest);
        return new BaseResponse<>(liveList);
    }

    @Operation(description = "라이브 생성(방송 시작) API", summary = "라이브 생성(방송 시작) API")
    @ApiResponse(responseCode = "200", description = "라이브 생성 성공 시 PUBLISHER token 반환")
    @PostMapping("/live")
    public BaseResponse<String> createLive(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @RequestPart CreateLiveRequest createLiveRequest, @RequestPart MultipartFile thumbnail) {
        String token = liveService.createLiveRoom(memberDetails, createLiveRequest, thumbnail);
        return new BaseResponse<>(token);
    }

    @Operation(description = "라이브 참가 API", summary = "라이브 참가 API")
    @ApiResponse(responseCode = "200", description = "해당 sessionId에 입장할 수 있는 SUBSCRIBER token 반환")
    @GetMapping("/live/{sessionId}")
    public BaseResponse<String> joinLive(@PathVariable String sessionId) {
        String token = liveService.joinLiveRoom(sessionId);
        return new BaseResponse<>(token);
    }

    @Operation(description = "라이브 녹화 시작 API", summary = "라이브 녹화 시작 API")
    @ApiResponse(responseCode = "200", description = "녹화를 시작하면서, 현재 녹화에 대한 recordingId를 반환")
    @PostMapping("/live/{sessionId}/recording/start")
    public BaseResponse<Object> recordingStart(@PathVariable String sessionId, @RequestBody RecordingStartRequest request) {
        String recordingId = liveService.recordingStart(sessionId, request);
        return new BaseResponse<>(recordingId);
    }

    @Operation(description = "라이브 녹화 중단 API", summary = "라이브 녹화 중단 API")
    @ApiResponse(responseCode = "200", description = "녹화 중단 성공")
    @PostMapping("/live/{sessionId}/recording/stop")
    public BaseResponse<Object> recordingStop(@RequestBody RecordingStopRequest request, @PathVariable String sessionId) {
        liveService.recordingStop(request);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @Operation(description = "라이브 녹화 저장 API", summary = "라이브 녹화 저장 API")
    @ApiResponse(responseCode = "200", description = "녹화 저장 성공")
    @PostMapping("/live/{sessionId}/recording/save")
    public BaseResponse<Object> recordingSave(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String sessionId, @Valid @RequestBody RecordingSaveRequest request) {
        if (request.getIsSaved()) {
            liveService.recordingSave(memberDetails, request);
        } else {
            liveService.deleteRecording(sessionId, request.getRecordingId());
        }
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }


    @ApiResponse(responseCode = "200", description = "업로드 성공")
    @PostMapping("/live/uploadRecord")
    @Operation(description = "프론트에서 사용 x",summary = "프론트에서 사용 X ")
    public BaseResponse<Object> uploadAtOpenviduServer(@RequestBody RecordingUploadAtOpenviduServerRequest request) throws JCodecException, IOException {

        liveService.uplooadAtOpenviduServer(request);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @Operation(description = "이전 녹화 목록 반환 API", summary = "이전 녹화 목록 반환 API")
    @ApiResponse(responseCode = "200", description = "녹화 목록 반환 성공", content = @Content(schema = @Schema(implementation = PrevRecordingListResponse.class)))
    @GetMapping("/live/{sessionId}/recording/list")
    public BaseResponse<List<PrevRecordingListResponse>> previousRecordingList(@PathVariable String sessionId) {
        List<PrevRecordingListResponse> response = liveService.getRecordings(sessionId);
        return new BaseResponse<>(response);
    }

    @Operation(description = "라이브 종료 API", summary = "라이브 종료 API")
    @ApiResponse(responseCode = "200", description = "라이브 종료 성공")
    @DeleteMapping("/live/{sessionId}")
    public BaseResponse<Object> terminateLive(@PathVariable String sessionId) {
        liveService.removeSession(sessionId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }
}

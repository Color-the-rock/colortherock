package org.anotherclass.colortherock.domain.live.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.live.request.CreateLiveRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingSaveRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingStartRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingStopRequest;
import org.anotherclass.colortherock.domain.live.response.LiveListResponse;
import org.anotherclass.colortherock.domain.live.response.RecordingListResponse;
import org.anotherclass.colortherock.domain.live.service.LiveService;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.jcodec.api.JCodecException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class LiveController {

    private final LiveService liveService;

    @Operation(description = "진행중인 라이브 목록 반환 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "목록 반환 성공", content = @Content(schema = @Schema(implementation = LiveListResponse.class))),
    })
    @GetMapping("/live/list")
    public BaseResponse<List<LiveListResponse>> getLiveList(@RequestParam(required = false) Long liveId) {
        List<LiveListResponse> liveList = liveService.getLiveList(liveId);
        return new BaseResponse<>(liveList);
    }

    @Operation(description = "라이브 생성(방송 시작) API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "라이브 생성 성공 시 PUBLISHER token 반환"),
    })
    @PostMapping("/live")
    public BaseResponse<String> createLive(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody CreateLiveRequest request) {
        String token = liveService.createLiveRoom(memberDetails, request);
        return new BaseResponse<>(token);
    }

    @Operation(description = "라이브 참가 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "해당 sessionId에 입장할 수 있는 SUBSCRIBER token 반환"),
    })
    @GetMapping("/live/{sessionId}")
    public BaseResponse<String> joinLive(@PathVariable String sessionId) {
        String token = liveService.joinLiveRoom(sessionId);
        return new BaseResponse<>(token);
    }

    @Operation(description = "라이브 녹화 시작 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "녹화를 시작하면서, 현재 녹화에 대한 recordingId를 반환"),
    })
    @PostMapping("/live/{sessionId}/recording/start")
    public BaseResponse<?> recordingStart(@PathVariable String sessionId, @RequestBody RecordingStartRequest request) {
        String recordingId = liveService.recordingStart(sessionId, request);
        return new BaseResponse<>(recordingId);
    }

    @Operation(description = "라이브 녹화 중단 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "녹화 중단 성공"),
    })
    @PostMapping("/live/{sessionId}/recording/stop")
    public BaseResponse<?> recordingStop(@RequestBody RecordingStopRequest request) {
        liveService.recordingStop(request);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @Operation(description = "라이브 녹화 저장 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "녹화 저장 성공"),
    })
    @PostMapping("/live/{sessionId}/recording/save")
    public BaseResponse<?> recordingSave(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String sessionId,@RequestBody RecordingSaveRequest request) throws IOException, JCodecException {
        liveService.recordingSave(memberDetails,sessionId,request);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @Operation(description = "이전 녹화 목록 반환 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "녹화 목록 반환 성공", content = @Content(schema = @Schema(implementation = LiveListResponse.class))),
    })
    @GetMapping("/live/{sessionId}/recording/list")
    public BaseResponse<?> recordingList(@PathVariable String sessionId) {
        List<RecordingListResponse> response = liveService.getRecordings(sessionId);
        return new BaseResponse<>(response);
    }
}

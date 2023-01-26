package org.anotherclass.colortherock.domain.live.cotroller;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.live.request.CreateLiveRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingSaveRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingStartRequest;
import org.anotherclass.colortherock.domain.live.request.RecordingStopRequest;
import org.anotherclass.colortherock.domain.live.service.LiveService;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class LiveController {

    private final LiveService liveService;

    @GetMapping("/live")
    public BaseResponse<String> createLive(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody CreateLiveRequest request) {
        String token = liveService.createLiveRoom(memberDetails, request);
        return new BaseResponse<>(token);

    }

    @GetMapping("/live/{sessionId}")
    public BaseResponse<String> joinLive(@PathVariable String sessionId) {

        String token = liveService.joinLiveRoom(sessionId);
        return new BaseResponse<>(token);
    }

    @PostMapping("/live/{sessionId}/recording/start")

    public BaseResponse<?> recordingStart(@PathVariable String sessionId, @RequestBody RecordingStartRequest request) {

        String recordingId = liveService.recordingStart(sessionId, request);

        return new BaseResponse<>(recordingId);
    }

    @PostMapping("/live/{sessionId}/recording/stop")
    public BaseResponse<?> recordingStop(@RequestBody RecordingStopRequest request) {

        liveService.recordingStop(request);

        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @PostMapping("/live/{sessionId}/recording/save")
    public BaseResponse<?> recordingSave(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String sessionId,@RequestBody RecordingSaveRequest request) {
        liveService.recordingSave(memberDetails,sessionId,request);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }
}

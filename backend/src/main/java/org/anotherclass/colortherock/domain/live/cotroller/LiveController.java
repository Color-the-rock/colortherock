package org.anotherclass.colortherock.domain.live.cotroller;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.live.request.CreateLiveRequest;
import org.anotherclass.colortherock.domain.live.service.LiveService;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.global.common.BaseResponse;
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
    public BaseResponse<String> joinLive(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String sessionId) {

        String token = liveService.joinLiveRoom(memberDetails, sessionId);
        return new BaseResponse<>(token);
    }

}

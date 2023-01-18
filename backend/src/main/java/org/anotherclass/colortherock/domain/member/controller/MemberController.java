package org.anotherclass.colortherock.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.request.ReGenerateAccessTokenRequest;
import org.anotherclass.colortherock.domain.member.response.ReGenerateAccessTokenResponse;
import org.anotherclass.colortherock.domain.member.service.MemberService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberDetails memberDetails) {

        return "ok";
    }

    @PostMapping("/refresh")
    public BaseResponse<ReGenerateAccessTokenResponse> reGenerateAccessToken(@Valid @RequestBody ReGenerateAccessTokenRequest request) {

        String regenerateAccessToken = memberService.regenerateAccessToken(request.getRefreshToken());
        return new BaseResponse<>(new ReGenerateAccessTokenResponse(regenerateAccessToken));
    }
}

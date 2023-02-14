package org.anotherclass.colortherock.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.request.DuplicateNicknameRequest;
import org.anotherclass.colortherock.domain.member.request.MemberSignUpRequest;
import org.anotherclass.colortherock.domain.member.request.ReGenerateAccessTokenRequest;
import org.anotherclass.colortherock.domain.member.response.MemberSignUpResponse;
import org.anotherclass.colortherock.domain.member.response.ReGenerateAccessTokenResponse;
import org.anotherclass.colortherock.domain.member.service.MemberService;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "member", description = "Member API")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final RecordService recordService;

    @GetMapping("/test")
    @Operation(description = "임시 테스트용 API", summary = "임시 테스트용 API")
    public String test() {
        return "ok";
    }

    @PostMapping("/api/refresh")
    @Operation(description = "액세스 재발급 API", summary = "액세스 토큰 재발급 API")
    @ApiResponse(responseCode = "200", description = "재발급 성공", content = @Content(schema = @Schema(implementation = ReGenerateAccessTokenResponse.class)))
    public BaseResponse<ReGenerateAccessTokenResponse> reGenerateAccessToken(@Valid @RequestBody ReGenerateAccessTokenRequest request) {

        String regenerateAccessToken = memberService.regenerateAccessToken(request.getRefreshToken());
        return new BaseResponse<>(new ReGenerateAccessTokenResponse(regenerateAccessToken));
    }

    @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = MemberSignUpResponse.class)))
    @Operation(description = "회원 가입 API", summary = "회원가입 API")
    @PostMapping("/api/member/signup")
    public BaseResponse<MemberSignUpResponse> signup(@Valid @RequestBody MemberSignUpRequest request) {
        MemberSignUpResponse signup = memberService.signup(request);
        recordService.saveNewRecord(signup.getId());
        return new BaseResponse<>(signup);
    }

    @PostMapping("/api/duplicateNickname")
    @Operation(description = "닉네임 중복 확인 API", summary = "닉네임 중복확인 API")
    @ApiResponse(responseCode = "200", description = "닉네임 중복 검사 통과", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @ApiResponse(responseCode = "400", description = "닉네임 중복 검사 실패")
    public BaseResponse<Object> duplicateNickname(@Valid @RequestBody DuplicateNicknameRequest request) {
        Boolean isDuplicate = memberService.duplicateNickname(request.getNickname());
        return new BaseResponse<>(isDuplicate);
    }

    @GetMapping("/testuser")
    @Operation(description = "요청받으면 토큰 생성해서 발급해줌 토큰 기한은 5분")
    public String testuser() {
        return memberService.testToken();
    }
}

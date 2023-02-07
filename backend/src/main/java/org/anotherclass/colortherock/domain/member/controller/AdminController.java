package org.anotherclass.colortherock.domain.member.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.request.LoginInfo;
import org.anotherclass.colortherock.domain.member.service.MemberService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "admin", description = "Admin API")
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {

    private final MemberService memberService;

    @PostMapping("/login/admin")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "아이디나 비밀번호 정보가 틀렸습니다.")
    })
    public BaseResponse<?> login(@RequestBody LoginInfo loginInfo) {
        String tokens = memberService.adminLogin(loginInfo);
        return new BaseResponse<>(tokens);
    }




}
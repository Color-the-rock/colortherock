package org.anotherclass.colortherock.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.report.request.PostReportRequest;
import org.anotherclass.colortherock.domain.report.service.ReportService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/video/board/detail")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/report")
    @Operation(description = "완등 영상 게시글 신고 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "신고 완료")
    })
    public BaseResponse<?> reportPost(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody PostReportRequest postReportRequest) {
        Member member = memberDetails.getMember();
        reportService.reportPost(member, postReportRequest);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

}


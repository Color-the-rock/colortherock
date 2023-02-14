package org.anotherclass.colortherock.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.AdminDetails;
import org.anotherclass.colortherock.domain.report.request.PostUnhiddenRequest;
import org.anotherclass.colortherock.domain.report.response.AdminReportDetailResponse;
import org.anotherclass.colortherock.domain.report.response.AdminReportedPostResponse;
import org.anotherclass.colortherock.domain.report.service.AdminReportService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.security.annotation.PreAuthorizeAdmin;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@PreAuthorizeAdmin
@RequestMapping("/api/admin")
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Operation(description = "관리자 - 숨김처리된 영상 게시글 보기 API", summary = "관리자 - 숨김처리된 영상 게시글 보기 API")
    @ApiResponse(responseCode = "200", description = "영상게시글 불러오기 성공")
    @GetMapping("/list")
    public BaseResponse<List<AdminReportedPostResponse>> getReportList(@AuthenticationPrincipal AdminDetails adminDetails) {
        if (adminDetails == null) {
            throw new GlobalBaseException(GlobalErrorCode.ACCESS_DENIED);
        }
        List<AdminReportedPostResponse> result = adminReportService.getReportedVideoBoard();
        return new BaseResponse<>(result);
    }

    @Operation(description = "관리자 - 신고 내용 확인하기 API", summary = "관리자 - 신고 내용 확인하기 API")
    @ApiResponse(responseCode = "200", description = "신고 내용 불러오기 성공")
    @GetMapping("/list/detail")
    public BaseResponse<List<AdminReportDetailResponse>> getReportDetail(@AuthenticationPrincipal AdminDetails adminDetails, @RequestParam Long videoBoardId) {
        if (adminDetails == null) {
            throw new GlobalBaseException(GlobalErrorCode.ACCESS_DENIED);
        }
        List<AdminReportDetailResponse> reportDetail = adminReportService.getReportDetail(videoBoardId);
        return new BaseResponse<>(reportDetail);
    }

    @Operation(description = "관리자 - 영상 숨김 해제 API", summary = "관리자 - 영상 숨김 해제 API")
    @ApiResponse(responseCode = "200", description = "영상 숨김 해제 성공")
    @PutMapping("list/detail/unhidden")
    public BaseResponse<Object> cancelHiddenStatus(@AuthenticationPrincipal AdminDetails adminDetails, @RequestBody PostUnhiddenRequest request) {
        if (adminDetails == null) {
            throw new GlobalBaseException(GlobalErrorCode.ACCESS_DENIED);
        }
        adminReportService.cancelHiddenStatus(request);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @Operation(description = "관리자 - 영상 삭제 API", summary = "관리자 - 영상 삭제 API")
    @ApiResponse(responseCode = "200", description = "영상 삭제 성공")
    @DeleteMapping("list/detail")
    public BaseResponse<Object> deleteVideo(@AuthenticationPrincipal AdminDetails adminDetails, @RequestParam Long videoBoardId) {
        if (adminDetails == null) {
            throw new GlobalBaseException(GlobalErrorCode.ACCESS_DENIED);
        }
        adminReportService.deleteReportedVideo(videoBoardId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

}

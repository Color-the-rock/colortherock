package org.anotherclass.colortherock.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.report.response.AdminReportDetailResponse;
import org.anotherclass.colortherock.domain.report.response.AdminReportedPostResponse;
import org.anotherclass.colortherock.domain.report.service.AdminReportService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminReportController {

    private final AdminReportService adminReportService;

    @GetMapping("/list")
    @Operation(description = "관리자 - 숨김처리된 영상 게시글 보기 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "영상게시글 불러오기 성공")
    })
    public BaseResponse<List<AdminReportedPostResponse>> getReportList() {
        List<AdminReportedPostResponse> result = adminReportService.getReportedVideoBoard();
        return new BaseResponse<>(result);
    }

    @GetMapping("/list/detail")
    @Operation(description = "관리자 - 신고 내용 확인하기 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "신고 내용 불러오기 성공")
    })
    public BaseResponse<List<AdminReportDetailResponse>> getReportDetail(@RequestParam Long videoBoardId) {
        List<AdminReportDetailResponse> reportDetail = adminReportService.getReportDetail(videoBoardId);
        return new BaseResponse<>(reportDetail);
    }

    @GetMapping("list/detail/unhidden")
    @Operation(description = "관리자 - 영상 숨김 해제 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "영상 숨김 해제 성공")
    })
    public BaseResponse<?> cancelHiddenStatus(@RequestParam Long videoBoardId) {
        adminReportService.cancelHiddenStatus(videoBoardId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @DeleteMapping("list/detail")
    @Operation(description = "관리자 - 영상 삭제 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "영상 삭제 성공")
    })
    public BaseResponse<?> deleteVideo(@RequestParam Long videoBoardId) {
        adminReportService.deleteReportedVideo(videoBoardId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

}

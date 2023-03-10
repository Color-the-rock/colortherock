package org.anotherclass.colortherock.domain.videocomment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.request.CommentUpdateRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.response.MyCommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.service.VideoCommentService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.security.annotation.PreAuthorizeMember;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Tag(name = "videoComment", description = "영상 댓글 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/videoboard")
public class VideoCommentController {

    private final VideoCommentService videoCommentService;

    @GetMapping("/comment")
    @Operation(description = "영상 댓글 조회 API", summary = "영상 댓글 조회 API")
    @ApiResponse(responseCode = "200", description = "댓글 조회 성공", content = @Content(schema = @Schema(implementation = CommentListResponse.class)))
    @PreAuthorizeMember
    public BaseResponse<List<CommentListResponse>> getCommentList(@Valid CommentListRequest condition) {
        List<CommentListResponse> commentList = videoCommentService.getCommentList(condition);
        return new BaseResponse<>(commentList);
    }

    @PostMapping("/comment")
    @Operation(description = "영상 댓글 작성 API", summary = "영상 댓글 작성 API")
    @ApiResponse(responseCode = "200", description = "댓글 등록 성공")
    @PreAuthorizeMember
    public BaseResponse<Object> addComment(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @RequestBody NewCommentRequest newCommentRequest) {
        Member member = memberDetails.getMember();
        videoCommentService.insertComment(member.getId(), newCommentRequest);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @PutMapping("/comment")
    @Operation(description = "영상 댓글 수정 API", summary = "영상 댓글 수정 API")
    @ApiResponse(responseCode = "200", description = "댓글 수정 완료")
    @ApiResponse(responseCode = "403", description = "유저 정보와 댓글 작성자 일치하지 않음")
    @ApiResponse(responseCode = "404", description = "해당하는 댓글 찾을 수 없음")
    @PreAuthorizeMember
    public BaseResponse<Object> updateComment(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @RequestBody CommentUpdateRequest commentUpdateRequest) {
        Member member = memberDetails.getMember();
        videoCommentService.updateComment(member.getId(), commentUpdateRequest);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @DeleteMapping("/comment")
    @Operation(description = "영상 댓글 삭제 API", summary = "영상 댓글 삭제 API")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 완료")
    @ApiResponse(responseCode = "403", description = "유저 정보와 댓글 작성자 일치하지 않음")
    @ApiResponse(responseCode = "404", description = "해당하는 댓글 찾을 수 없음")
    @PreAuthorizeMember
    public BaseResponse<Object> deleteComment(@AuthenticationPrincipal MemberDetails memberDetails, @NotNull @RequestParam Long commentId) {
        Member member = memberDetails.getMember();
        videoCommentService.deleteComment(member.getId(), commentId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @GetMapping("/mycomment")
    @Operation(description = "내 영상 댓글 조회 API", summary = "내 영상 댓글 조회 API")
    @ApiResponse(responseCode = "200", description = "나의 댓글 조회 완료")
    @PreAuthorizeMember
    public BaseResponse<List<MyCommentListResponse>> getMyCommentList
            (@AuthenticationPrincipal MemberDetails memberDetails, @RequestParam(required = false) Long storeId) {
        Member member = memberDetails.getMember();
        List<MyCommentListResponse> myCommentList = videoCommentService.getMyCommentList(member.getId(), storeId);
        return new BaseResponse<>(myCommentList);
    }
}


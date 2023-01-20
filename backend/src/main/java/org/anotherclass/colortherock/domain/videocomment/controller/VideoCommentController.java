package org.anotherclass.colortherock.domain.videocomment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.request.CommentUpdateRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.service.VideoCommentService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Tag(name = "videoComment", description = "영상 댓글 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/video/board")
public class VideoCommentController {

    private final VideoCommentService videoCommentService;

    /**
     * 영상 댓글 조회
     *
     * @param condition 현재 페이지의 마지막 아이디, 조회하고자 하는 게시판 아이디 값
     * @param pageable  Pageable 객체(컨트롤러에서 생성)
     */

    @GetMapping("/comment")
    public BaseResponse<List<CommentListResponse>> getCommentList
    (CommentListRequest condition, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<CommentListResponse> commentList = videoCommentService.getCommentList(condition, pageable);
        return new BaseResponse<>(commentList);
    }

    /**
     * 영상 댓글 작성
     */

    @PostMapping("/comment")
    public BaseResponse<?> addComment(@AuthenticationPrincipal MemberDetails memberDetails, @Valid NewCommentRequest newCommentRequest) {
        Member member = memberDetails.getMember();
        videoCommentService.insertComment(member.getId(), newCommentRequest);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    /**
     * 영상 댓글 수정
     */

    @PutMapping("/comment")
    public BaseResponse<?> updateComment(@AuthenticationPrincipal MemberDetails memberDetails, @Valid CommentUpdateRequest commentUpdateRequest) {
        Member member = memberDetails.getMember();
        videoCommentService.updateComment(member.getId(), commentUpdateRequest);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    /**
     * 영상 댓글 삭제
     */
    @DeleteMapping("/comment")
    public BaseResponse<?> deleteComment(@AuthenticationPrincipal MemberDetails memberDetails, @NotNull Long commentId) {
        Member member = memberDetails.getMember();
        videoCommentService.deleteComment(member.getId(), commentId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

}

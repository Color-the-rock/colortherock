package org.anotherclass.colortherock.domain.videocomment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.service.VideoCommentService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

}

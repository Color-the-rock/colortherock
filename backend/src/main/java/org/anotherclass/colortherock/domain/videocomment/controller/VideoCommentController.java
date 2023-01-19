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

    @GetMapping("/comment")
    public BaseResponse<List<CommentListResponse>> getCommentList
            (@PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, CommentListRequest cond) {
        List<CommentListResponse> commentList = videoCommentService.getCommentList(cond, pageable);
        return new BaseResponse<>(commentList);
    }

}

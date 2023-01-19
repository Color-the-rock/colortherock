package org.anotherclass.colortherock.domain.videocomment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentReadRepository;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoCommentService {

    private final VideoCommentReadRepository videoCommentReadRepository;

    /**
     * 영상 댓글 조회
     * @param condition 현재 페이지의 마지막 아이디, 조회하고자 하는 게시판 아이디 값
     * @param pageable Pageable 객체(컨트롤러에서 생성)
     */

    @Transactional(readOnly = true)
    public List<CommentListResponse> getCommentList(CommentListRequest condition, Pageable pageable) {
        Slice<VideoComment> slices = videoCommentReadRepository.searchBySlice(condition, pageable);
        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vc -> CommentListResponse.builder()
                        .commentId(vc.getId())
                        .nickname(vc.getMember().getNickname())
                        .content(vc.getContent())
                        .writtenTime(vc.getWrittenTime())
                        .build()).collect(Collectors.toList());
    }


}



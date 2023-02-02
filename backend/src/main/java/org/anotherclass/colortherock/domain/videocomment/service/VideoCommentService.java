package org.anotherclass.colortherock.domain.videocomment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.exception.CommentNotFoundException;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentReadRepository;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentRepository;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.request.CommentUpdateRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.response.MyCommentListResponse;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
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

    private final MemberRepository memberRepository;
    private final VideoBoardRepository videoBoardRepository;
    private final VideoCommentReadRepository videoCommentReadRepository;
    private final VideoCommentRepository videoCommentRepository;


    @Transactional(readOnly = true)
    public List<CommentListResponse> getCommentList(CommentListRequest condition) {
        Pageable pageable = Pageable.ofSize(15);
        Slice<VideoComment> slices = videoCommentReadRepository.searchByCond(condition, pageable);
        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vc -> CommentListResponse.builder()
                        .commentId(vc.getId())
                        .nickname(vc.getMember().getNickname())
                        .content(vc.getContent())
                        .createdDate(vc.getCreatedDate())
                        .build()).collect(Collectors.toList());
    }

    @Transactional
    public Long insertComment(Long memberId, NewCommentRequest newCommentRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalBaseException(GlobalErrorCode.USER_NOT_FOUND));
        VideoBoard videoBoard = videoBoardRepository.findById(newCommentRequest.getVideoBoardId())
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));

        VideoComment videoComment = videoCommentRepository.save(VideoComment.builder()
                .content(newCommentRequest.getContent())
                .member(member)
                .videoBoard(videoBoard)
                .build());
        return videoComment.getId();
    }

    @Transactional
    public void updateComment(Long memberId, CommentUpdateRequest commentUpdateRequest) {
        VideoComment comment = videoCommentRepository.findById(commentUpdateRequest.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(GlobalErrorCode.COMMENT_NOT_FOUND));
        checkAuth(memberId, comment);
        comment.update(commentUpdateRequest.getContent());
    }

    @Transactional
    public void deleteComment(Long memberId, Long commentId) {
        VideoComment comment = videoCommentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(GlobalErrorCode.COMMENT_NOT_FOUND));
        checkAuth(memberId, comment);
        videoCommentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<MyCommentListResponse> getMyCommentList(Long memberId, Long storeId) {
        Pageable pageable = Pageable.ofSize(15);
        Slice<VideoComment> slices = videoCommentReadRepository.getMyComments(memberId, storeId, pageable);
        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vc -> MyCommentListResponse.builder()
                        .commentId(vc.getId())
                        .videoBoardId(vc.getVideoBoard().getId())
                        .nickname(vc.getMember().getNickname())
                        .content(vc.getContent())
                        .createdDate(vc.getCreatedDate())
                        .build()).collect(Collectors.toList());
    }

    // 받은 멤버가 수정권한이 있는지 확인하는 메서드
    private void checkAuth(Long memberId, VideoComment comment) {
        if (!comment.getMember().getId().equals(memberId)) {
            throw new GlobalBaseException(GlobalErrorCode.NOT_WRITER);
        }
    }

}



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
import org.anotherclass.colortherock.domain.videocomment.exception.NotWriterException;
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


    /**
     * 댓글 목록 요청 로직 구현
     *
     * @param condition @see {@link CommentListRequest}
     * @return @see {@link CommentListResponse} 리스트 형태로 반환
     */
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
                        .createdDate(vc.getCreatedDate().toLocalDate())
                        .build()).collect(Collectors.toList());
    }

    /**
     * 새로운 댓글을 생성하는 로직
     *
     * @param memberId          댓글 생성하는 멤버 id
     * @param newCommentRequest 새로운 댓글 내용 , 게시글 id 포함 {@inheritDoc}
     * @return 작성한 댓글의 id 반환
     */
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

    /**
     * 댓글 수정 로직
     *
     * @param memberId             사용자 id
     * @param commentUpdateRequest {@link CommentUpdateRequest} 새로운 댓글 내용, 댓글 id
     */

    @Transactional
    public void updateComment(Long memberId, CommentUpdateRequest commentUpdateRequest) {
        VideoComment comment = videoCommentRepository.findById(commentUpdateRequest.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(GlobalErrorCode.COMMENT_NOT_FOUND));
        checkAuth(memberId, comment);
        comment.update(commentUpdateRequest.getContent());
    }

    /**
     * 댓글 삭제 로직
     *
     * @param memberId  사용자 id
     * @param commentId 삭제하려는 댓글 id
     */
    @Transactional
    public void deleteComment(Long memberId, Long commentId) {
        VideoComment comment = videoCommentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(GlobalErrorCode.COMMENT_NOT_FOUND));
        checkAuth(memberId, comment);
        videoCommentRepository.delete(comment);
    }

    /**
     * 내 댓글만 조회하는 로직
     *
     * @param memberId 인증된 사용자 id
     * @param storeId 마지막으로 본 댓글 id
     * @return {@link MyCommentListResponse} 내 댓글 list 형태로 반환
     */
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
                        .title(vc.getVideoBoard().getTitle())
                        .content(vc.getContent())
                        .createdDate(vc.getCreatedDate().toLocalDate())
                        .build()).collect(Collectors.toList());
    }

    /**
     * 사용자와 댓글을 쓴 사용자가 일치하는지 체크하는 로직
     * @param memberId 사용자 id
     * @param comment 댓글 id
     */
    private void checkAuth(Long memberId, VideoComment comment) {
        if (!comment.getMember().getId().equals(memberId)) {
            throw new NotWriterException(GlobalErrorCode.NOT_WRITER);
        }
    }

}



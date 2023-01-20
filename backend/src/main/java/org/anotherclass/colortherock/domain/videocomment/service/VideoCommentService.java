package org.anotherclass.colortherock.domain.videocomment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentReadRepository;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentRepository;
import org.anotherclass.colortherock.domain.videocomment.request.CommentListRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public Long insertComment(Long memberId, NewCommentRequest newCommentRequest) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<VideoBoard> videoBoard = videoBoardRepository.findById(newCommentRequest.getVideoBoardId());
        Long commentId = videoCommentRepository.save(VideoComment.builder()
                .content(newCommentRequest.getContent())
                .writtenTime(newCommentRequest.getWrittenTime())
                .member(member.get())
                .videoBoard(videoBoard.get())
                .build()).getId();
        return commentId;
    }


}



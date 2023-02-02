package org.anotherclass.colortherock.domain.videoboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.exception.VideoUserMismatchException;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.exception.WriterMismatchException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardReadRepository;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessPostUpdateRequest;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessVideoUploadRequest;
import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardDetailResponse;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoBoardService {
    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;
    private final VideoBoardRepository videoBoardRepository;
    private final VideoBoardReadRepository videoBoardReadRepository;

    // 완등 영상 전체 리스트 조회
    @Transactional(readOnly = true)
    public List<VideoBoardSummaryResponse> getSuccessVideos(VideoBoardSearchRequest condition) {
        Pageable pageable = Pageable.ofSize(16);

        Slice<VideoBoard> slices = videoBoardReadRepository.searchByCond(condition, pageable);

        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vb -> VideoBoardSummaryResponse.builder()
                        .videoBoardId(vb.getId())
                        .title(vb.getTitle())
                        .thumbnailURL(vb.getVideo().getThumbnailURL())
                        .color(vb.getVideo().getColor())
                        .createdDate(vb.getCreatedDate())
                        .gymName(vb.getVideo().getGymName())
                        .build()).collect(Collectors.toList());

    }

    @Transactional
    public Long uploadMySuccessVideoPost(Long memberId, SuccessVideoUploadRequest successVideoUploadRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GlobalBaseException(GlobalErrorCode.USER_NOT_FOUND));
        Video video = videoRepository.findById(successVideoUploadRequest.getVideoId())
                .orElseThrow(() -> new VideoNotFoundException(GlobalErrorCode.VIDEO_NOT_FOUND));
        if (!video.getMember().getId().equals(memberId)) {
            throw new VideoUserMismatchException(GlobalErrorCode.NOT_VIDEO_OWNER);
        }

        VideoBoard newVideoBoard = videoBoardRepository.save(VideoBoard.builder()
                .title(successVideoUploadRequest.getTitle())
                .isHidden(false)
                .video(video)
                .member(member)
                .build());

        return newVideoBoard.getId();
    }

    // 완등 영상 게시글 상세 조회
    @Transactional(readOnly = true)
    public VideoBoardDetailResponse getVideoDetail(Long videoBoardId) {
        VideoBoard vb = videoBoardRepository.findById(videoBoardId)
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        return VideoBoardDetailResponse.builder()
                .videoBoardId(vb.getId())
                .nickname(vb.getMember().getNickname())
                .title(vb.getTitle())
                .createdDate(vb.getCreatedDate())
                .build();
    }

    // 완등 영상 게시글 수정
    @Transactional
    public void updateSuccessPost(Long memberId, SuccessPostUpdateRequest successPostUpdateRequest) {
        VideoBoard vb = videoBoardRepository.findById(successPostUpdateRequest.getVideoBoardId())
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        checkAuth(memberId, vb);
        vb.update(successPostUpdateRequest.getTitle());
    }

    // 완등 영상 게시글 삭제
    @Transactional
    public void deleteSuccessPost(Long memberId, Long videoBoardId) {
        VideoBoard vb = videoBoardRepository.findById(videoBoardId)
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        checkAuth(memberId, vb);
        videoBoardRepository.delete(vb);
    }

    // 내가 작성한 완등 게시글 조회
    @Transactional(readOnly = true)
    public List<VideoBoardSummaryResponse> getMySuccessVideoPosts(Long memberId, Long storeId) {
        Pageable pageable = Pageable.ofSize(8);

        Slice<VideoBoard> slices = videoBoardReadRepository.getMySuccessPosts(memberId, storeId, pageable);

        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vb -> VideoBoardSummaryResponse.builder()
                        .videoBoardId(vb.getId())
                        .title(vb.getTitle())
                        .thumbnailURL(vb.getVideo().getThumbnailURL())
                        .color(vb.getVideo().getColor())
                        .createdDate(vb.getCreatedDate())
                        .build()).collect(Collectors.toList());
    }


    // 받은 멤버가 수정권한이 있는지 확인하는 메서드
    private void checkAuth(Long memberId, VideoBoard videoBoard) {
        if (!videoBoard.getMember().getId().equals(memberId)) {
            throw new WriterMismatchException(GlobalErrorCode.NOT_WRITER);
        }
    }

}

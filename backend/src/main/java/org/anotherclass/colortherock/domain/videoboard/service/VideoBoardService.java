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
import org.anotherclass.colortherock.domain.videoboard.response.ColorCodeKorean;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardDetailResponse;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
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
public class VideoBoardService {
    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;
    private final VideoBoardRepository videoBoardRepository;
    private final VideoBoardReadRepository videoBoardReadRepository;
    private static final Integer PAGE_SIZE = 16;


    /**
     *
     * @param condition
     * @return
     */
    @Transactional(readOnly = true)
    public List<VideoBoardSummaryResponse> getSuccessVideos(VideoBoardSearchRequest condition) {
        Pageable pageable = Pageable.ofSize(PAGE_SIZE);

        Slice<VideoBoard> slices = videoBoardReadRepository.searchByCond(condition, pageable);

        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vb ->
                    VideoBoardSummaryResponse.builder()
                            .videoBoardId(vb.getId())
                            .title(vb.getTitle())
                            .thumbnailURL(vb.getVideo().getThumbnailURL())
                            .color(vb.getVideo().getColor())
                            .createdDate(vb.getCreatedDate().toLocalDate())
                            .gymName(vb.getVideo().getGymName())
                            .colorCode(ColorCodeKorean.getColor(vb.getVideo().getColor()))
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

    // ?????? ?????? ????????? ?????? ??????
    @Transactional(readOnly = true)
    public VideoBoardDetailResponse getVideoDetail(Long videoBoardId) {
        VideoBoard vb = videoBoardRepository.findById(videoBoardId)
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        if(Boolean.TRUE.equals(vb.getIsHidden())) return null;
        return VideoBoardDetailResponse.builder()
                .videoBoardId(vb.getId())
                .nickname(vb.getMember().getNickname())
                .title(vb.getTitle())
                .s3URL(vb.getVideo().getS3URL())
                .createdDate(vb.getCreatedDate().toLocalDate())
                .build();
    }

    // ?????? ?????? ????????? ??????
    @Transactional
    public void updateSuccessPost(Long memberId, SuccessPostUpdateRequest request) {
        VideoBoard vb = videoBoardRepository.findById(request.getVideoBoardId())
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        checkAuth(memberId, vb);
        vb.update(request.getTitle());
        vb.getVideo().update(request.getLevel(), request.getGymName(), request.getColor());
    }

    // ?????? ?????? ????????? ??????
    @Transactional
    public void deleteSuccessPost(Long memberId, Long videoBoardId) {
        VideoBoard vb = videoBoardRepository.findById(videoBoardId)
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        checkAuth(memberId, vb);
        // ????????? isPosted ??????
        vb.getVideo().postDeleted();
        videoBoardRepository.delete(vb);
    }

    // ?????? ????????? ?????? ????????? ??????
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
                        .createdDate(vb.getCreatedDate().toLocalDate())
                        .build()).collect(Collectors.toList());
    }


    // ?????? ????????? ??????????????? ????????? ???????????? ?????????
    private void checkAuth(Long memberId, VideoBoard videoBoard) {
        if (!videoBoard.getMember().getId().equals(memberId)) {
            throw new WriterMismatchException(GlobalErrorCode.NOT_WRITER);
        }
    }

}

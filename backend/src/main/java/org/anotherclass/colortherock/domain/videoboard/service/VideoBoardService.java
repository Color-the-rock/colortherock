package org.anotherclass.colortherock.domain.videoboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardReadRepository;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessPostUpdateRequest;
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
    private final VideoBoardRepository videoBoardRepository;
    private final VideoBoardReadRepository videoBoardReadRepository;

    // 완등 영상 전체 리스트 조회
    @Transactional(readOnly = true)
    public List<VideoBoardSummaryResponse> getSuccessVideos(VideoBoardSearchRequest condition, Pageable pageable) {
        Slice<VideoBoard> slices = videoBoardReadRepository.searchBySlice(condition, pageable);

        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vb -> VideoBoardSummaryResponse.builder()
                        .videoBoardId(vb.getId())
                        .title(vb.getTitle())
                        .thumbnailURL(vb.getVideo().getThumbnailURL())
                        .color(vb.getVideo().getColor())
                        .writtenTime(vb.getWrittenTime())
                        .build()).collect(Collectors.toList());

    }

    // 완등 영상 게시글 상세 조회
    @Transactional(readOnly = true)
    public VideoBoardDetailResponse getVideoDetail(Long videoBoardId) {
        VideoBoard vb = videoBoardRepository.findById(videoBoardId)
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.NO_SUCH_POST));
        return VideoBoardDetailResponse.builder()
                .nickname(vb.getMember().getNickname())
                .title(vb.getTitle())
                .writtenTime(vb.getWrittenTime())
                .build();
    }

    // 완등 영상 게시글 수정
    @Transactional
    public void updateSuccessPost(Long memberId, SuccessPostUpdateRequest successPostUpdateRequest) {
        VideoBoard vb = videoBoardRepository.findById(successPostUpdateRequest.getVideoBoardId())
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.NO_SUCH_POST));
        checkAuth(memberId, vb);
        vb.update(successPostUpdateRequest.getTitle(), successPostUpdateRequest.getWrittenTime());
    }

    // 받은 멤버가 수정권한이 있는지 확인하는 메서드
    private void checkAuth(Long memberId, VideoBoard videoBoard) {
        if (!videoBoard.getMember().getId().equals(memberId)) {
            throw new GlobalBaseException(GlobalErrorCode.WRITER_MISMATCH);
        }
    }

}

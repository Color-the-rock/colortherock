package org.anotherclass.colortherock.domain.videoboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoBoardService {
    private final VideoBoardRepository videoBoardRepository;

    /**
     * 완등 영상 전체 리스트 조회
     * @param condition 현재 페이지의 마지막 아이디, 암장, 색상 검색 조건을 담고 있는 객체
     * @param pageable Pageable 객체 (컨트롤러에서 생성)
     */
    @Transactional
    public List<VideoBoardSummaryResponse> getSuccessVideos(VideoBoardSearchRequest condition, Pageable pageable) {
        Slice<VideoBoard> slices = videoBoardRepository.searchBySlice(condition, pageable);

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

}

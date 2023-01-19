package org.anotherclass.colortherock.domain.videoboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
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

    @Transactional(readOnly = true)
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

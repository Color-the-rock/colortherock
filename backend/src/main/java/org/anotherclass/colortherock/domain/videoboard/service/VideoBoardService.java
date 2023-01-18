package org.anotherclass.colortherock.domain.videoboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryDto;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoBoardService {

    private final VideoBoardRepository videoBoardRepository;
    private final VideoRepository videoRepository;

    /**
     * 완등 영상 전체 리스트 조회
     */
    @Transactional
    public List<VideoBoardSummaryDto> getSuccessVideoList(Pageable pageable) {
        Slice<VideoBoard> slices = videoBoardRepository.findSliceBy(pageable);
        if (slices.isEmpty()) {
            return new ArrayList<>();
        }

        return slices.toList().stream()
                .map(vb -> {
                    Optional<Video> video = videoRepository.findById(vb.getVideo().getId());
                    return VideoBoardSummaryDto.builder()
                            .videoBoardId(vb.getId())
                            .title(vb.getTitle())
                            .thumbnailURL(video.get().getThumbnailURL())
                            .color(video.get().getColor())
                            .build();
                }).collect(Collectors.toList());

    }

}

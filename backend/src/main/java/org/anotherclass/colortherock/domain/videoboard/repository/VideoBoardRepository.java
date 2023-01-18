package org.anotherclass.colortherock.domain.videoboard.repository;

import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VideoBoardRepository extends PagingAndSortingRepository<VideoBoard, Long> {

    // 비디오 영상 가져오기
    Slice<VideoBoard> findAllBy(Pageable pageable);
}

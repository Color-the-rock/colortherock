package org.anotherclass.colortherock.domain.videoboard.repository;

import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoBoardRepository extends JpaRepository<VideoBoard, Long> {
}

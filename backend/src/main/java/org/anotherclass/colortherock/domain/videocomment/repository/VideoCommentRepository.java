package org.anotherclass.colortherock.domain.videocomment.repository;

import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCommentRepository extends JpaRepository<VideoComment, Long> {

}

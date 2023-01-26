package org.anotherclass.colortherock.domain.video.repository;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {

    // videoId에 해당하는 영상 가져오기
    Optional<Video> findById(Long videoId);
    List<Video> findAllByMember(Member member);
    List<Video> findAllByMemberAndShootingDate(Member member, LocalDate date);
}

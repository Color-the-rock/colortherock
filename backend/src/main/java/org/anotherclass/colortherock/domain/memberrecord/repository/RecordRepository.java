package org.anotherclass.colortherock.domain.memberrecord.repository;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByMember(Member member);
    List<Video> findAllByMemberAndShootingDate(Member member, LocalDate date);
}

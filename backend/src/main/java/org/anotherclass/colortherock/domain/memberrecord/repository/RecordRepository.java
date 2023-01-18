package org.anotherclass.colortherock.domain.memberrecord.repository;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Video, Long> {
    Long countByMemberAndLevel(Member member, int level);
    Long countByMemberAndLevelAndIsSuccessIsTrue(Member member, int level);
}

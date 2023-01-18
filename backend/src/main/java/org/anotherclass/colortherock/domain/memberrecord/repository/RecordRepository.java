package org.anotherclass.colortherock.domain.memberrecord.repository;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<MemberRecord, Long> {
    MemberRecord findByMember(Member member);
}

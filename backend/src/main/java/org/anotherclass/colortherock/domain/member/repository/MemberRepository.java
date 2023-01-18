package org.anotherclass.colortherock.domain.member.repository;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

}

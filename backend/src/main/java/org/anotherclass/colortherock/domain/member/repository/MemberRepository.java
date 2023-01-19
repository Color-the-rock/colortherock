package org.anotherclass.colortherock.domain.member.repository;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.Member.RegistrationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByRegistrationIdAndEmail(RegistrationId registrationId, String email);
    Member findByEmail(String email);

}

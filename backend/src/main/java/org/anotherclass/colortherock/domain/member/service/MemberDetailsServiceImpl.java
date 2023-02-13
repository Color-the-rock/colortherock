package org.anotherclass.colortherock.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.AdminDetails;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {

    @Value("${spring.security.user.name}")
    private String adminId;

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        if (!input.equals(adminId)) {
            Member member = Optional.ofNullable(memberRepository.findByEmail(input)).orElseThrow(UserNotFoundException::new);
            return new MemberDetails(member);
        } else return new AdminDetails(input);
    }
}
package org.anotherclass.colortherock.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.AdminDetails;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {

    @Value("${spring.security.user.name}")
    private String adminId;

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        if (!input.equals(adminId)) {
            MemberDetails memberDetails = new MemberDetails(memberRepository.findByEmail(input));
            if (memberDetails == null) {
                throw new UsernameNotFoundException("User Not Found");
            } else return memberDetails;
        } else return new AdminDetails(input);
    }
}

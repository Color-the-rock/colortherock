package org.anotherclass.colortherock.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.exception.AccessDeniedException;
import org.anotherclass.colortherock.domain.member.exception.IncorrectAdminInfoException;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.member.request.LoginInfo;
import org.anotherclass.colortherock.domain.member.request.MemberSignUpRequest;
import org.anotherclass.colortherock.domain.member.response.MemberSignUpResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MemberService {
    private final JwtTokenUtils jwtTokenUtils;
    private final MemberRepository memberRepository;
    private Long memberId;

    @Value("${spring.security.user.name}")
    private String adminId;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    public String adminLogin(LoginInfo loginInfo) {
        if(!adminId.equals(loginInfo.getId()) || !adminPassword.equals(loginInfo.getPassword())) {
            throw new IncorrectAdminInfoException();
        }
        return "Bearer " + jwtTokenUtils.createTokens(loginInfo.getId(), List.of(() -> "ROLE_ADMIN"));
    }

    public String regenerateAccessToken(String refreshToken) {
        Optional<RefreshToken> findToken = jwtTokenUtils.isValidRefreshToken(refreshToken);
        RefreshToken findRefreshToken = findToken.orElseThrow(() -> new AccessDeniedException(GlobalErrorCode.ACCESS_DENIED));
        return jwtTokenUtils.reCreateTokens(findRefreshToken);
    }

    public MemberSignUpResponse signup(MemberSignUpRequest request) {
        Member member = request.toEntity();
        Member save = memberRepository.save(member);
        return new MemberSignUpResponse(save.getId(), save.getEmail(), save.getRegistrationId(), save.getNickname());
    }

    public boolean duplicateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            return false;
        }
        return true;
    }

    @PostConstruct
    public void initTestUser() {
        Member member = Member.builder()
                .registrationId(Member.RegistrationId.google)
                .email("test@test.com")
                .nickname("닉네임")
                .build();

        Member save = memberRepository.save(member);
        memberId = save.getId();
    }
    public String testToken() {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}

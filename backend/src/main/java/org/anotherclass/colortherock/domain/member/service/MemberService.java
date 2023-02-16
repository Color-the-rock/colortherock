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

import static org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils.BEARER_PREFIX;

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

    /**
     * 관리자 계정 로그인
     *
     * @param loginInfo 로그인 정보
     * @return 관리자 계정 토큰
     */
    public String adminLogin(LoginInfo loginInfo) {
        if (!adminId.equals(loginInfo.getId()) || !adminPassword.equals(loginInfo.getPassword())) {
            throw new IncorrectAdminInfoException();
        }
        return BEARER_PREFIX + jwtTokenUtils.createTokens(loginInfo.getId(), List.of(() -> "ROLE_ADMIN"));
    }

    /**
     * 리프레시 토큰으로 액세스 토큰을 다시 만듬
     *
     * @param refreshToken 리프레시 토큰
     * @return 다시 만든 액세스 토큰
     */
    public String regenerateAccessToken(String refreshToken) {
        Optional<RefreshToken> findToken = jwtTokenUtils.findRefreshToken(refreshToken);
        RefreshToken findRefreshToken = findToken.orElseThrow(() -> new AccessDeniedException(GlobalErrorCode.ACCESS_DENIED));
        return jwtTokenUtils.reCreateTokens(findRefreshToken);
    }

    /**
     * 회원 가입 로직
     *
     * @param request {@link MemberSignUpRequest} 회원가입 요청
     * @return {@link MemberSignUpResponse} 회원 가입 응답
     */

    public MemberSignUpResponse signup(MemberSignUpRequest request) {
        Member member = request.toEntity();
        Member save = memberRepository.save(member);
        String token = BEARER_PREFIX + jwtTokenUtils.createTokens(save, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
        RefreshToken refreshToken = jwtTokenUtils.generateRefreshToken(token);
        return new MemberSignUpResponse(save.getId(), save.getEmail(), save.getRegistrationId(), save.getNickname(), refreshToken.getAccessTokenValue(), refreshToken.getRefreshTokenKey());
    }

    /**
     * 닉네임 중복체크 검사
     *
     * @param nickname 검사할 닉네임
     * @return true 면 중복이 아님
     */
    public boolean duplicateNickname(String nickname) {
        return !memberRepository.existsByNickname(nickname);
    }

    @PostConstruct
    public void initTestUser() {
        Member member = Member.builder()
                .registrationId(Member.RegistrationId.kakao)
                .email("suker80@naver.com")
                .nickname("닉네임")
                .build();

        Member save = memberRepository.save(member);
        memberId = save.getId();
    }

    public String testToken() {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }
}

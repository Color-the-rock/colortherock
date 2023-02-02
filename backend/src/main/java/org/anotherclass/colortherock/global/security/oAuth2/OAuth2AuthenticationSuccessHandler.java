package org.anotherclass.colortherock.global.security.oAuth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenUtils jwtTokenUtils;
    private final MemberRepository memberRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        MemberInfo memberInfo = MemberInfoFactory.getMemberInfo(attributes,(OAuth2AuthenticationToken)authentication);

        Optional<Member> optionalMember = memberRepository.findByRegistrationIdAndEmail(memberInfo.getRegistrationId(), memberInfo.getEmail());
        String targetUrl;
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            String tokens = "Bearer " + jwtTokenUtils.createTokens(member, oAuth2User.getAuthorities());
            RefreshToken token = jwtTokenUtils.generateRefreshToken(tokens);
            response.setHeader(AUTHORIZATION, tokens);
            targetUrl = UriComponentsBuilder.newInstance()
                    .path("/oauth")
                    .queryParam("refresh", token.getRefreshToken())
                    .queryParam("access", token.getAccessToken())
                    .queryParam("email", member.getEmail())
                    .queryParam("registrationId", member.getRegistrationId())
                    .queryParam("nickname", member.getNickname()).build().toUriString();
        } else {
            targetUrl = UriComponentsBuilder.newInstance()
                    .path("/oauth")
                    .queryParam("email", memberInfo.getEmail())
                    .queryParam("registrationId", memberInfo.getRegistrationId()).toUriString();
        }
        response.sendRedirect(targetUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}



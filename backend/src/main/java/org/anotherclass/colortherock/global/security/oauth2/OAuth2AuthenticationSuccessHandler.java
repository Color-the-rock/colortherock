package org.anotherclass.colortherock.global.security.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils.BEARER_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author suker80
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenUtils jwtTokenUtils;
    private final MemberRepository memberRepository;

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        MemberInfo memberInfo = MemberInfoFactory.getMemberInfo(attributes, (OAuth2AuthenticationToken) authentication);

        Optional<Member> optionalMember = memberRepository.findByRegistrationIdAndEmail(memberInfo.getRegistrationId(), memberInfo.getEmail());
        String targetUrl;
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            String tokens = BEARER_PREFIX + jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
            RefreshToken token = jwtTokenUtils.generateRefreshToken(tokens);
            response.setHeader(AUTHORIZATION, tokens);
            targetUrl = UriComponentsBuilder.newInstance()
//                    .scheme("http")
//                    .host("localhost")
//                    .port(3000)
                    .path("/oauth")
                    .queryParam("refresh", token.getRefreshTokenKey())
                    .queryParam("access", token.getAccessTokenValue())
                    .queryParam("email", member.getEmail())
                    .queryParam("registrationId", member.getRegistrationId())
                    .queryParam("nickname", URLEncoder.encode(member.getNickname(), StandardCharsets.UTF_8)).toUriString();
        } else {
            targetUrl = UriComponentsBuilder.newInstance()
//                    .scheme("http")
//                    .host("localhost")
//                    .port(3000)
                    .path("/oauth")
                    .queryParam("email", memberInfo.getEmail())
                    .queryParam("registrationId", memberInfo.getRegistrationId()).toUriString();
        }
        log.info("{}", targetUrl);

        clearAuthenticationAttributes(request,response);
        response.sendRedirect(targetUrl);
    }

    // HttpCookie 삭제
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}



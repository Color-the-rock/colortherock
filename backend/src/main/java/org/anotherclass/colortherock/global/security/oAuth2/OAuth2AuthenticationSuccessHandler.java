package org.anotherclass.colortherock.global.security.oAuth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenUtils jwtTokenUtils;
    private final MemberRepository repository;
    private final String targetUrl = "http://localhost:8080";

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return super.determineTargetUrl(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Member member = new Member("suker800@gmail.com","hi");
        String tokens = "Bearer " + jwtTokenUtils.createTokens(member, oAuth2User.getAuthorities());
        RefreshToken token = jwtTokenUtils.generateRefreshToken(tokens);
        response.setHeader(AUTHORIZATION, tokens);
        StringBuilder sb = new StringBuilder();
        sb.append(targetUrl);
        sb.append("/login/oauth2/code/google");
//        sb.append("token=").append(tokens).append("&");
//        sb.append("name=").append(member.getName()).append("&");
//        sb.append("id=").append(member.getId());
        log.info("tartgetUrl {}",sb);
//        response.sendRedirect(sb.toString());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}



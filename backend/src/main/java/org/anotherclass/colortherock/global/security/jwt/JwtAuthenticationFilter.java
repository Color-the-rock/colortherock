package org.anotherclass.colortherock.global.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.anotherclass.colortherock.global.security.jwt.JwtAuthenticationProvider.getGrantedAuthorities;
import static org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils.BEARER_PREFIX;
import static org.aspectj.util.LangUtil.isEmpty;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtTokenUtils jwtTokenUtils;


    private static final String KEY_ROLES = "roles";


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        super(authenticationManager);
        this.jwtTokenUtils = jwtTokenUtils;
    }

    /**
     * 매 요청마다 현재 요청이 인증이 되어있는지 판단하는 필터
     * @exception io.jsonwebtoken.JwtException JWT 관련 예외
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isEmpty(header) || !header.startsWith(BEARER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);
        Claims claims;
        try {
            claims = jwtTokenUtils.getAllClaims(token);
        } catch (Exception e) {
            log.info("jwt exception message : {} token : {}", e.getMessage(), token);
            chain.doFilter(request, response);
            return;
        }
        Collection<? extends GrantedAuthority> grantedAuthorities = createGrantedAuthorities(claims);
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(grantedAuthorities, token);
        Authentication authenticate = this.getAuthenticationManager().authenticate(authenticationToken);
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
        chain.doFilter(request, response);
    }

    /**
     * claim에서 유저 권한을 가져와서 Collection 타입으로 변환한다
     * @param claims 해독이 된 claim
     * @return {@link GrantedAuthority}의 Collcetion 타입
     */
    private Collection<? extends GrantedAuthority> createGrantedAuthorities(Claims claims) {
        return getGrantedAuthorities(claims, KEY_ROLES);
    }

}


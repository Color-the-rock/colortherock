package org.anotherclass.colortherock.global.security.jwt;

import io.jsonwebtoken.Claims;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.aspectj.util.LangUtil.isEmpty;

public class JwtAuthorizeFilter extends BasicAuthenticationFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final String bearer_prefix = "Bearer ";

    private static final String KEY_ROLES = "roles";

    public JwtAuthorizeFilter(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        super(authenticationManager);
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isEmpty(header) || !header.startsWith(bearer_prefix)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);

        Claims claims = jwtTokenUtils.getAllClaims(token);
        Collection<? extends GrantedAuthority> grantedAuthorities = createGrantedAuthorities(claims);
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(grantedAuthorities, token);
        Authentication authenticate = this.getAuthenticationManager().authenticate(authenticationToken);
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
        chain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> createGrantedAuthorities(Claims claims) {
        List<Map<String, String>> roles = (List<Map<String, String>>) claims.get(KEY_ROLES);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Map<String, String> role : roles) {
            grantedAuthorities.add(() -> role.get("authority"));

        }
        return grantedAuthorities;
    }

}


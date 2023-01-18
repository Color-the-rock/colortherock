package org.anotherclass.colortherock.global.security.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.aspectj.util.LangUtil.isEmpty;

public class JwtAuthorizeFilter extends BasicAuthenticationFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final String bearer_prefix = "Bearer ";

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
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(null, token);
        Authentication authenticate = this.getAuthenticationManager().authenticate(authenticationToken);
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
    }
}


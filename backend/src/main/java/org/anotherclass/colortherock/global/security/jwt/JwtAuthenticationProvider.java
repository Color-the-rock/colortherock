package org.anotherclass.colortherock.global.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static final String KEY_ROLES = "roles";
    private final JwtTokenUtils jwtTokenUtils;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Claims claims = jwtTokenUtils.getAllClaims(((JwtAuthenticationToken) authentication).getToken());
        Collection<? extends GrantedAuthority> grantedAuthorities = createGrantedAuthorities(claims);

        MemberDetails memberDetails = new MemberDetails(Member.builder().email((String) claims.get("email")).build());
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(grantedAuthorities, "");
        jwtAuthenticationToken.setDetails(memberDetails);
        return jwtAuthenticationToken;
    }

    private Collection<? extends GrantedAuthority> createGrantedAuthorities(Claims claims) {
        List<Map<String,String>> roles = (List<Map<String, String>>) claims.get(KEY_ROLES);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Map<String,String> role : roles) {
            grantedAuthorities.add(() -> role.get("authority"));

        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
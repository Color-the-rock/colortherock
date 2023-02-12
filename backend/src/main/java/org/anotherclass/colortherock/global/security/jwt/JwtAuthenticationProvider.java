package org.anotherclass.colortherock.global.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.AdminDetails;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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


    static Collection<GrantedAuthority> getGrantedAuthorities(Claims claims, String keyRoles) {
        List<Map<String, String>> roles = (List<Map<String, String>>) claims.get(keyRoles);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Map<String, String> role : roles) {
            grantedAuthorities.add(() -> role.get("authority"));

        }
        return grantedAuthorities;
    }

    /**
     * Jwt 토큰을 인증한다.
     * @param authentication 인증을 수행할 객체 {@link JwtAuthenticationToken} 을 인증한다
     * @return 인증에 성공한 {@link JwtAuthenticationToken}을 반환
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Claims claims = jwtTokenUtils.getAllClaims(((JwtAuthenticationToken) authentication).getToken());
        Collection<? extends GrantedAuthority> grantedAuthorities = createGrantedAuthorities(claims);

        String principal = (String) claims.get("email");
        UserDetails userDetails;

        if (principal != null) {
            userDetails = new MemberDetails(Member.builder()
                    .email((String) claims.get("email"))
                    .id(Long.valueOf((Integer) claims.get("id")))
                    .registrationId(Member.RegistrationId.valueOf((String) claims.get("registrationId")))
                    .build());
        } else {
            userDetails = new AdminDetails((String) claims.get("adminId"));
        }

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(grantedAuthorities, ((JwtAuthenticationToken) authentication).getToken());
        jwtAuthenticationToken.setDetails(userDetails);
        jwtAuthenticationToken.setAuthenticated(true);
        return jwtAuthenticationToken;
    }

    /**
     * claim에서 유저 권한을 가져와서 Collection 타입으로 변환한다
     * @param claims jwt 토큰을 해독한 claim
     * @return {@link GrantedAuthority}의 Collcetion 타입
     */
    private Collection<? extends GrantedAuthority> createGrantedAuthorities(Claims claims) {
        return getGrantedAuthorities(claims, KEY_ROLES);
    }

    /**
     * 현재 Authentcation 객체가 인증방식을 지원하는 타입인지 판단
     * @param authentication 인증을 수행할 Authentcatio 인스턴스
     * @return true, false로 provider와 authentcation 객체가 지원하는지 판단.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
package org.anotherclass.colortherock.global.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;

    /**
     * Spring security에서 이용할 JwtToken 객체 권한과 토큰을 받아서 생성한다.
     * @param authorities 권한
     * @param token token
     */
    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String token) {
        super(authorities);
        this.token = token;
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return getDetails();
    }

    public String getToken() {
        return token;
    }
}
package org.anotherclass.colortherock.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.exception.AccessDeniedException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.redis.RefreshTokenRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenUtils {
    private static final String key = "secret";
    private static final long exp = 300 * 1000;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenUtils(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createTokens(Member member, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("registrationId", member.getRegistrationId().name());
        map.put("id", member.getId());
        map.put("roles", authorities);
        return createTokens(map);
    }

    public String createTokens(Map<String, Object> claims) {
        return createTokens(Jwts.claims(claims));
    }

    public String createTokens(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String createTokens(Claims claims, String refreshToken) {

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        refreshTokenRepository.update(refreshToken, accessToken);
        return accessToken;
    }


    public RefreshToken generateRefreshToken(String accessToken) {

        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), accessToken);
        return refreshTokenRepository.save(refreshToken);

    }

    public Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public boolean isExpired(String token) {
        try {
            Claims allClaims = getAllClaims(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public Optional<RefreshToken> isValidRefreshToken(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken);

    }

    public String reCreateTokens(RefreshToken refreshToken) {

        try {
            getAllClaims(refreshToken.getAccessToken());
            throw new AccessDeniedException(GlobalErrorCode.ACCESS_DENIED);
        } catch (ExpiredJwtException e) {
            Claims claims = e.getClaims();
            return createTokens(claims, refreshToken.getRefreshToken());
        }

    }
}


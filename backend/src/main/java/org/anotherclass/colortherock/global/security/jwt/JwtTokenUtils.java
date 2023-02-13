package org.anotherclass.colortherock.global.security.jwt;

import io.jsonwebtoken.*;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.exception.AccessDeniedException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.redis.RefreshTokenRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenUtils {
    private static final String KEY = "secret";
    private static final long EXP = 300 * 1000L * 12;
    public static final String BEARER_PREFIX = "Bearer ";
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenUtils(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * 멤버와 유저 권한을 받아 토큰을 만든다.
     * @param member 토큰을 만들 Member 객체
     * @param authorities member 객체의 유저 권한
     * @return 만들어진 액세스 토큰 반환
     * @author suker80
     */
    public String createTokens(Member member, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("registrationId", member.getRegistrationId().name());
        map.put("id", member.getId());
        map.put("roles", authorities);
        return createTokens(map);
    }

    /** 관리자 계정 토큰 만드는 메소드
     * @param adminId admin 계정의 id
     * @param authorities admin 유저의 유저 권한
     * @return 만들어진 액세스 토큰 반환
     */
    public String createTokens(String adminId, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> map = new HashMap<>();
        map.put("adminId", adminId);
        map.put("roles", authorities);
        return createTokens(map);
    }

    /**
     *
     * @param claims 토큰을 만들 key value 형태의 클레임 객체
     * @return 만들어진 액세스 토큰 반환
     */
    public String createTokens(Map<String, Object> claims) {
        return createTokens(Jwts.claims(claims));
    }

    /**
     * JWT 라이브러리를 통해서 액세스 토큰을 생성하는 메소드
     * @param claims Map 객체가 Claims로 변환 됌
     * @return 만들어진 액세스 토큰
     */
    public String createTokens(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    /**
     * 로그인에 성공하면 리프레시 토큰을 생성하고 redis에 저장한다.
     * @param accessToken 리프레시 토큰을 key로 액세스 토큰을 value로 저장한다.
     * @return redis에 저장한 refresh token 인스턴스
     */

    public RefreshToken generateRefreshToken(String accessToken) {

        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), accessToken);
        return refreshTokenRepository.save(refreshToken);

    }

    /**
     * 액세스 토큰을 기반으로 모든 클레임을 가져온다.
     * @param token 해독할 토큰
     * @return jwt 토큰을 해독한 claim
     * @author suker80
     * @exception ExpiredJwtException jwt 만료시 발생되는 exception
     * @exception InvalidClaimException 유효하지 않은 클레임 exception
     * @see JwtException JwtException 클래스 참조
     */
    public Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }


    /**
     * redis에서 refresh 토큰을 찾는다.
     * @param refreshToken redis에서 refresh 토큰을 찾을 key
     * @return redis에서 찾은 refresh 토큰 객체, Optional로 감싼다.\
     * @author suker80
     */
    public Optional<RefreshToken> findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken);

    }

    /** 사용자의 액세스 토큰이 만료되었다면 다시 액세스 토큰을 만든다.
     *
     * @param refreshToken redis에서 찾은 refersh Token 인스턴스
     * @return 새로 만든 액세스 토큰
     */
    public String reCreateTokens(RefreshToken refreshToken) {

        try {
            getAllClaims(refreshToken.getAccessTokenValue());
            throw new AccessDeniedException(GlobalErrorCode.ACCESS_DENIED);
        } catch (ExpiredJwtException e) {
            Claims claims = e.getClaims();
            String accessToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + EXP))
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    .compact();
            refreshTokenRepository.update(refreshToken.getRefreshTokenKey(), accessToken);
            return accessToken;
        }

    }
}


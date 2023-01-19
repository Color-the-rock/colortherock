package org.anotherclass.colortherock.member;


import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.member.request.ReGenerateAccessTokenRequest;
import org.anotherclass.colortherock.domain.member.response.ReGenerateAccessTokenResponse;
import org.anotherclass.colortherock.global.redis.RefreshTokenRepository;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberLoginTest extends IntegrationTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    public static final String AUTHORIZATION_HEADER = "Bearer ";

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    EntityManager em;
    @Autowired
    MockMvc mockMvc;
    String url = "http://localhost:8080/";
    Member member;

    @BeforeEach
    public void setMember() {
        member = new Member("suker800@gmail.com", "태규");
        em.persist(member);

        em.flush();
        em.clear();

    }

    @Test
    @DisplayName("비 로그인 요청시 403 에러")
    public void 비로그인시_403에러를_반환() throws Exception {
        mockMvc.perform(
                get(url + "test")
        ).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @DisplayName("정상적인 로그인 요청")
    public void 정상적인_로그인_요청() throws Exception {

        String tokens = jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_USER")));

        mockMvc.perform(
                        get(url + "test")
                                .header("Authorization", AUTHORIZATION_HEADER + tokens
                                ))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Refresh 발급")
    public void Refresh_발급() {
        String tokens = jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        RefreshToken refreshToken = jwtTokenUtils.generateRefreshToken(tokens);
        System.out.println("refreshToken.getRefreshToken() = " + refreshToken.getRefreshToken());
        System.out.println("refreshToken.getAccessToken() = " + refreshToken.getAccessToken());

    }

    @Test
    @DisplayName("Refresh로 다시 토큰 재발급 했는데 아직 만료안되서 실패")
    public void 토큰재발급실패() throws Exception {

        String tokens = jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        RefreshToken refreshToken = jwtTokenUtils.generateRefreshToken(tokens);
        Optional<RefreshToken> validRefreshToken = jwtTokenUtils.isValidRefreshToken(refreshToken.getRefreshToken());
        Assertions.assertDoesNotThrow(() -> {
            validRefreshToken.orElseThrow();
        });
        refreshToken = validRefreshToken.orElseThrow();
        ReGenerateAccessTokenRequest request = new ReGenerateAccessTokenRequest(tokens, refreshToken.getRefreshToken());

        mockMvc.perform(
                post("/refresh")
                        .content(this.objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.status", Matchers.is(HttpStatus.UNAUTHORIZED.value())));
    }
    @Test
    @DisplayName("Refresh로 다시 토큰 재발급")
    public void 토큰재발급성공() throws Exception {

        String tokens = jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        RefreshToken refreshToken = jwtTokenUtils.generateRefreshToken(tokens);
        Optional<RefreshToken> validRefreshToken = jwtTokenUtils.isValidRefreshToken(refreshToken.getRefreshToken());
        Assertions.assertDoesNotThrow(() -> {
            validRefreshToken.orElseThrow();
        });
        refreshToken = validRefreshToken.orElseThrow();
        ReGenerateAccessTokenRequest request = new ReGenerateAccessTokenRequest(tokens, refreshToken.getRefreshToken());
        Thread.sleep(3000);
        String contentAsString = mockMvc.perform(
                        post("/refresh")
                                .content(this.objectMapper.writeValueAsBytes(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ReGenerateAccessTokenResponse response = objectMapper.readValue(contentAsString, ReGenerateAccessTokenResponse.class);
        String accessToken = response.getAccessToken();
        Assertions.assertNotEquals(accessToken, tokens);

    }

}

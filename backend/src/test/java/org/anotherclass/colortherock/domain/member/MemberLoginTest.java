package org.anotherclass.colortherock.domain.member;


import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.member.request.MemberSignUpRequest;
import org.anotherclass.colortherock.domain.member.request.ReGenerateAccessTokenRequest;
import org.anotherclass.colortherock.domain.member.response.ReGenerateAccessTokenResponse;
import org.anotherclass.colortherock.global.redis.RefreshTokenRepository;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.anotherclass.colortherock.global.security.jwt.RefreshToken;
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

import static org.hamcrest.Matchers.*;
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
        member = new Member("suker800@gmail.com", "태규", Member.RegistrationId.google);

        em.persist(member);

        em.flush();
        em.clear();

    }

    @Test
    @DisplayName("비 로그인 요청시 302 에러")
    public void 비로그인시_302에러를_반환() throws Exception {
        mockMvc.perform(
                get(url + "test")
        ).andExpect(status().is(302));
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
        ).andExpect(jsonPath("$.status", is(HttpStatus.UNAUTHORIZED.value())));
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


    @Test
    @DisplayName("회원 가입 API 테스트")
    public void 회원가입() throws Exception {
        MemberSignUpRequest request = new MemberSignUpRequest("a@a.com", Member.RegistrationId.kakao, "이름");

        mockMvc.perform(
                        post(url + "/api/member/signup")
                                .content(objectMapper.writeValueAsBytes(request))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.status", is(200)))
                .andDo(print());
    }

    @Test
    @DisplayName("이메일 중복검사 API")
    public void 이메일이_중복일_경우() throws Exception {

        mockMvc.perform(post(url + "/api/duplicateNickname").content("태규").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.status", is(400)));

    }

    @Test
    @DisplayName("이메일 중복검사 API")
    public void 이메일이_중복이_아닐_경우() throws Exception {

        mockMvc.perform(post(url + "/api/duplicateNickname").content("태규123").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.status", is(200)));

    }
}

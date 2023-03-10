package org.anotherclass.colortherock.domain.member;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.member.request.DuplicateNicknameRequest;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils.BEARER_PREFIX;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("NonAsciiCharacters")
class MemberLoginTest extends IntegrationTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    public static final String AUTHORIZATION_HEADER = BEARER_PREFIX;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    EntityManager em;
    @Autowired
    MockMvc mockMvc;
    String url = "http://localhost:8080/";
    Member member;

    @BeforeEach
    void setMember() {
        member = new Member("suker800@gmail.com", "??????", Member.RegistrationId.google);

        em.persist(member);

        em.flush();
        em.clear();

    }

    @Test
    @DisplayName("??? ????????? ????????? 302 ??????")
    void ???????????????_401?????????_??????() throws Exception {
        mockMvc.perform(
                get(url + "test")
        ).andExpect(status().is(401));
    }

    @Test
    @DisplayName("???????????? ????????? ??????")
    void ????????????_?????????_??????() throws Exception {

        String tokens = jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));

        mockMvc.perform(
                        get(url + "test")
                                .header("Authorization", AUTHORIZATION_HEADER + tokens
                                ))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Refresh ??????")
    void Refresh_??????() {
        String tokens = jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
        RefreshToken refreshToken = jwtTokenUtils.generateRefreshToken(tokens);
        System.out.println("refreshToken.getRefreshToken() = " + refreshToken.getRefreshTokenKey());
        System.out.println("refreshToken.getAccessToken() = " + refreshToken.getAccessTokenValue());
        Assertions.assertNotNull(refreshToken.getRefreshTokenKey());
        Assertions.assertNotNull(refreshToken.getAccessTokenValue());

    }

    @Test
    @DisplayName("?????? ????????? ??????")
    void ?????????_??????() throws Exception {
        url += "test";
        mockMvc.perform(get(url)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer awefaefs"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    @DisplayName("Refresh??? ?????? ?????? ????????? ????????? ?????? ??????????????? ??????")
    void ?????????????????????() throws Exception {

        String tokens = jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
        RefreshToken refreshToken = jwtTokenUtils.generateRefreshToken(tokens);
        Optional<RefreshToken> validRefreshToken = jwtTokenUtils.findRefreshToken(refreshToken.getRefreshTokenKey());
        Assertions.assertDoesNotThrow(() -> {
            validRefreshToken.orElseThrow();
        });
        refreshToken = validRefreshToken.orElseThrow();
        ReGenerateAccessTokenRequest request = new ReGenerateAccessTokenRequest(tokens, refreshToken.getRefreshTokenKey());

        mockMvc.perform(
                post("/api/refresh")
                        .content(this.objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.status", is(HttpStatus.UNAUTHORIZED.value())));
    }

    @Test
    @DisplayName("Refresh??? ?????? ?????? ?????????")
    void ?????????????????????() throws Exception {

        Map<String, Object> map = new HashMap<>();
        Claims claims = Jwts.claims(map);
        String tokens = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        RefreshToken refreshToken = jwtTokenUtils.generateRefreshToken(tokens);
        Optional<RefreshToken> validRefreshToken = jwtTokenUtils.findRefreshToken(refreshToken.getRefreshTokenKey());
        Assertions.assertDoesNotThrow(() -> {
            validRefreshToken.orElseThrow();
        });
        refreshToken = validRefreshToken.orElseThrow();
        ReGenerateAccessTokenRequest request = new ReGenerateAccessTokenRequest(tokens, refreshToken.getRefreshTokenKey());
        String contentAsString = mockMvc.perform(
                        post("/api/refresh")
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
    @DisplayName("?????? ?????? API ?????????")
    void ????????????() throws Exception {
        MemberSignUpRequest request = new MemberSignUpRequest("a@a.com", Member.RegistrationId.kakao, "??????");

        mockMvc.perform(
                        post(url + "/api/member/signup")
                                .content(objectMapper.writeValueAsBytes(request))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(jsonPath("$.status", is(200)))
                .andDo(print());
    }

    @Test
    @DisplayName("????????? ???????????? API")
    void ????????????_?????????_??????() throws Exception {

        DuplicateNicknameRequest request = new DuplicateNicknameRequest("??????");
        mockMvc.perform(post(url + "/api/duplicateNickname").content(objectMapper.writeValueAsBytes(request)).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(jsonPath("$.result", is(false)));

    }

    @Test
    @DisplayName("????????? ???????????? API")
    void ????????????_?????????_??????_??????() throws Exception {
        DuplicateNicknameRequest request = new DuplicateNicknameRequest("??????123");
        mockMvc.perform(post(url + "/api/duplicateNickname").content(objectMapper.writeValueAsBytes(request)).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(jsonPath("$.result", is(true)));

    }
}

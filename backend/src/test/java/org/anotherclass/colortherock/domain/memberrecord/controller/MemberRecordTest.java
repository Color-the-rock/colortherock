package org.anotherclass.colortherock.domain.memberrecord.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.anotherclass.colortherock.global.redis.RefreshTokenRepository;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberRecordTest extends IntegrationTest {

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
    VideoRepository videoRepository;
    @Autowired
    MockMvc mockMvc;
    String url = "http://localhost:8080/api";
    Member member;
    String token;
    Video video;
    @BeforeEach
    public void setMemberAndToken() {
        // Member 추가 및 token 설정
        member = new Member("johan@rock.com", "조한", Member.RegistrationId.google);
        Member savedMember = memberRepository.save(member);
        token = jwtTokenUtils.createTokens(savedMember, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        // 영상 추가
        video = new UploadVideoRequest(LocalDate.parse("2023-01-17"), 1, "더클라임 강남", true, "노랑", savedMember).toEntity();
        videoRepository.save(video);
    }

    @Test
    @DisplayName("[GET]전체 영상 색상 별 통계 조회")
    public void 레벨9개_통계조회() throws Exception {
        mockMvc.perform(
            get(url + "/record/color")
            .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[0].level").isNumber());
    }

    @Test
    @DisplayName("[GET]날짜별 운동 기록 색상 통계 조회")
    public void 레벨9개_날짜별_통계조회() throws Exception {
        mockMvc.perform(
                        get(url + "/record/color/2023-01-17")
                                .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[0].level").isNumber())
                .andExpect(jsonPath("$.result[0].success").value(1));
    }

    @Test
    @DisplayName("[GET]날짜별 운동 기록 색상 통계 조회 - 실패")
    public void 레벨9개_날짜별_통계조회_실패() throws Exception {
        mockMvc.perform(
                        get(url + "/record/color/2023-13-17")
                                .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(jsonPath("$.status", is(400)));
    }

}

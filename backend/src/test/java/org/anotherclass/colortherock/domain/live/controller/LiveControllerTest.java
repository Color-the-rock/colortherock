package org.anotherclass.colortherock.domain.live.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LiveControllerTest extends IntegrationTest {

    String url = "http://localhost:8080/api";
    String AUTHORIZATION_HEADER = "Bearer ";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    VideoRepository videoRepository;
    Member member;
    String token;

    @BeforeEach
    public void setMember() {
        member = new Member("johan@rock.com", "조한", Member.RegistrationId.google);
        Member savedMember = memberRepository.save(member);
        token = jwtTokenUtils.createTokens(savedMember, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    @DisplayName("[POST] 라이브 영상 S3 업로드")
    public void 라이브영상_S3_업로드() throws Exception {
        mockMvc.perform(
                        post(url + "/live/test/recording/save")
                                .header("Authorization", AUTHORIZATION_HEADER + token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "\t\"level\": 1,\n" +
                                        "\t\"title\": \"test제목입니다.\",\n" +
                                        "\t\"gymName\": \"더 클라임 강남\",\n" +
                                        "\t\"isSuccess\": true,\n" +
                                        "\t\"color\": \"빨강\",\n" +
                                        "\t\"recordingId\": \"test\"\n" +
                                        "}"))
                .andExpect(status().isOk());
        List<Video> allByMember = videoRepository.findAll();
        Assertions.assertEquals(1, allByMember.size());
        Assertions.assertEquals(1, allByMember.get(0).getLevel());
        Assertions.assertEquals("빨강", allByMember.get(0).getColor());
    }
}
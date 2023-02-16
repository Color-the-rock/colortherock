package org.anotherclass.colortherock.domain.report.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.report.request.PostReportRequest;
import org.anotherclass.colortherock.domain.report.request.PostUnhiddenRequest;
import org.anotherclass.colortherock.domain.report.response.AdminReportDetailResponse;
import org.anotherclass.colortherock.domain.report.response.AdminReportedPostResponse;
import org.anotherclass.colortherock.domain.report.service.ReportService;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils.BEARER_PREFIX;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AdminReportControllerTest extends IntegrationTest {

    @Autowired
    MockMvc mockMvc;
    String url = "http://localhost:8080/api/admin/list";

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoBoardRepository videoBoardRepository;
    @Autowired
    private ReportService reportService;

    private ArrayList<Long> memberIds;
    private ArrayList<Long> videoBoardIds;

    @Value("${spring.security.user.name}")
    private String adminId;

    private String token;
    private String tokenForUser;
    @Autowired
    JwtTokenUtils jwtTokenUtils;


    @BeforeEach
    void setup() {
        memberIds = new ArrayList<>();
        videoBoardIds = new ArrayList<>();
        // Member, Video, VideoBoard 생성
        for (int i = 0; i < 10; i++) {
            Member member = new Member(i + "@rock.com", i + "user", Member.RegistrationId.kakao);
            memberRepository.save(member);
            memberIds.add(member.getId());
            Video video = Video.builder()
                    .shootingDate(LocalDate.parse("2022-01-30"))
                    .level(1)
                    .gymName("더클라이밍")
                    .s3URL("url")
                    .videoName("name")
                    .isSuccess(true)
                    .thumbnailURL("url")
                    .thumbnailName("name")
                    .color("초록")
                    .member(member)
                    .build();
            videoRepository.save(video);
            VideoBoard videoBoard = VideoBoard.builder()
                    .title("나 성공함")
                    .isHidden(false)
                    .video(video)
                    .member(member)
                    .build();
            videoBoardRepository.save(videoBoard);
            videoBoardIds.add(videoBoard.getId());
        }

        // 신고 생성
        for (int i = 1; i < 6; i++) {
            Member member = memberRepository.findById(memberIds.get(i))
                    .orElseThrow(() -> new GlobalBaseException(GlobalErrorCode.USER_NOT_FOUND));
            for (int j = 0; j < 2; j++) {
                reportService.reportPost(member, new PostReportRequest(videoBoardIds.get(0), "TYPE_A"));
            }
        }

    }

    @Test
    @DisplayName("(관리자 인증된 경우) 신고된 영상 게시글 목록 가져오기")
    void getReportedPost() throws Exception {
        token = BEARER_PREFIX + jwtTokenUtils.createTokens(adminId, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, token)
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn().getResponse();

        BaseResponse<List<AdminReportedPostResponse>> result = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        assertEquals(3, result.getResult().size());
    }

    @Test
    @DisplayName("(관리자 미인증시) 신고된 영상 불러오기 실패")
    void failToGetReportPost() throws Exception {
        mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(401)));
    }

    @Test
    @DisplayName("(일반 유저의 경우) 신고된 영상 불러오기 실패")
    void failToGetReportPostByUser() throws Exception {
        Member member = memberRepository.findById(memberIds.get(0))
                .orElseThrow(() -> new GlobalBaseException(GlobalErrorCode.USER_NOT_FOUND));
        tokenForUser = token = BEARER_PREFIX + jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
        mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, tokenForUser)
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(401)));
    }

    @Test
    @DisplayName("신고 내용 상세 확인하기")
    void getReportedPostDetail() throws Exception {
        token = BEARER_PREFIX + jwtTokenUtils.createTokens(adminId, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        MockHttpServletResponse response = mockMvc.perform(
                        get(url + "/detail")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("videoBoardId", String.valueOf(videoBoardIds.get(0)))
                                .header(HttpHeaders.AUTHORIZATION, token)
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn().getResponse();
        BaseResponse<List<AdminReportDetailResponse>> result = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        assertEquals(10, result.getResult().size());
    }

    @Test
    @DisplayName("영상 숨김 해제하기")
    void cancelHiddenStatus() throws Exception {
        token = BEARER_PREFIX + jwtTokenUtils.createTokens(adminId, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        PostUnhiddenRequest request = new PostUnhiddenRequest(videoBoardIds.get(0));
        mockMvc.perform(
                        put(url + "/detail/unhidden")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(request))
                                .header(HttpHeaders.AUTHORIZATION, token)
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)));
    }

    @Test
    @DisplayName("영상 삭제하기")
    void deleteVideo() throws Exception {
        token = BEARER_PREFIX + jwtTokenUtils.createTokens(adminId, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        mockMvc.perform(
                        delete(url + "/detail")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("videoBoardId", String.valueOf(videoBoardIds.get(0)))
                                .header(HttpHeaders.AUTHORIZATION, token)
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)));
    }

}
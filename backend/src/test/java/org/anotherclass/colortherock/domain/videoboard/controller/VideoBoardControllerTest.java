package org.anotherclass.colortherock.domain.videoboard.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessPostUpdateRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class VideoBoardControllerTest extends IntegrationTest {

    @Autowired
    EntityManager em;
    @Autowired
    MockMvc mockMvc;
    Member member;
    String url = "http://localhost:8080/api/video/board/";

    @Autowired
    VideoBoardRepository videoBoardRepository;
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    private String token;

    ArrayList<Long> videoBoardIds;

    @BeforeEach
    public void setup() {
        member = Member.builder()
                .nickname("yeji")
                .email("yeji@email.com")
                .registrationId(Member.RegistrationId.kakao).build();
        em.persist(member);
        videoBoardIds = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Video video = Video.builder()
                    .thumbnailURL("url")
                    .videoName("이름")
                    .s3URL("s3")
                    .color("초록" + i)
                    .level(3)
                    .gymName("더클라이밍 강남점" + i)
                    .isSuccess(true)
                    .shootingDate(LocalDate.now())
                    .member(member)
                    .build();
            em.persist(video);
            VideoBoard videoBoard = VideoBoard.builder()
                    .video(video)
                    .title("제목" + i)
                    .isHidden(false)
                    .member(member)
                    .build();
            em.persist(videoBoard);
            videoBoardIds.add(videoBoard.getId());
        }
        token = TOKEN_PREFIX + jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    @DisplayName("완등 영상 게시글 전체 조회")
    void getSuccessVideoPosts() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();

        BaseResponse<List<VideoBoardSummaryResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        assertEquals(16, arrayList.getResult().size());
    }

    @Test
    @DisplayName("완등 영상 슬라이싱 조회")
    void getSuccessPostsSlice() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("storeId", String.valueOf(videoBoardIds.get(2)))
                )
                .andReturn()
                .getResponse();

        BaseResponse<List<VideoBoardSummaryResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        assertEquals(16, arrayList.getResult().size());
    }

    @Test
    @DisplayName("완등 영상 암장별 조회")
    void getSuccessPostsByGym() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("gymName", "더클라이밍 강남점1")
                )
                .andReturn()
                .getResponse();
        BaseResponse<List<VideoBoardSummaryResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        assertEquals(1, arrayList.getResult().size());
    }

    @Test
    @DisplayName("완등 영상 색상별 조회")
    void getSuccessPostsByColor() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("color", "초록1")
                )
                .andReturn()
                .getResponse();
        BaseResponse<List<VideoBoardSummaryResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        assertEquals(1, arrayList.getResult().size());
    }

    @Test
    @DisplayName("완등 영상 게시글 상세 조회")
    void getSuccessPostDetail() throws Exception {
        url += "detail";
        mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("videoBoardId", String.valueOf(videoBoardIds.get(0)))
                )
                .andExpect(jsonPath("$.result.title", is("제목0")));
    }

    @Test
    @DisplayName("완등 영상 게시글 제목 수정")
    void updateSuccessPost() throws Exception {
        url += "detail";
        Long videoBoardId = videoBoardIds.get(0);
        SuccessPostUpdateRequest request = new SuccessPostUpdateRequest(videoBoardId, "새로운내용");
        mockMvc.perform(
                        put(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(request))
                                .header(HttpHeaders.AUTHORIZATION, token)

                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)));

        Optional<VideoBoard> byId = videoBoardRepository.findById(videoBoardId);
        VideoBoard videoBoard = byId.orElseThrow();
        String title = videoBoard.getTitle();
        assertEquals("새로운내용", title);
    }

    @Test
    @DisplayName("완등 영상 게시글 삭제")
    void deleteSuccessPost() throws Exception{
        url += "detail";
        Long videoBoardId = videoBoardIds.get(0);
        mockMvc.perform(
                delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("videoBoardId", String.valueOf(videoBoardId))
                        .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpect(jsonPath("$.status", is(200)));

        Optional<VideoBoard> byId = videoBoardRepository.findById(videoBoardId);
        assertTrue(byId.isEmpty());
    }

    @Test
    @DisplayName("내 완등 영상 조회 param 미입력시 최신순 16개까지 반환")
    void getMySuccessPostList() throws Exception {
        url += "mypost";
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, token)
                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn().getResponse();

        BaseResponse<List<VideoBoardSummaryResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        assertEquals(5, arrayList.getResult().size());
    }


}
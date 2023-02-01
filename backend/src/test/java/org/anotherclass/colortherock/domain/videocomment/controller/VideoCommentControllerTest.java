package org.anotherclass.colortherock.domain.videocomment.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentRepository;
import org.anotherclass.colortherock.domain.videocomment.request.CommentUpdateRequest;
import org.anotherclass.colortherock.domain.videocomment.request.NewCommentRequest;
import org.anotherclass.colortherock.domain.videocomment.response.CommentListResponse;
import org.anotherclass.colortherock.domain.videocomment.response.MyCommentListResponse;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.junit.jupiter.api.Assertions;
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
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class VideoCommentControllerTest extends IntegrationTest {

    @Autowired
    EntityManager em;

    @Autowired
    MockMvc mockMvc;
    Member member;
    private Video video;
    private VideoBoard videoBoard;
    String url = "http://localhost:8080/api/videoboard/";

    @Autowired
    VideoCommentRepository videoCommentRepository;
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    private String token;

    @BeforeEach()
    public void setup() {


        member = Member.builder()
                .nickname("이름")
                .email("이메일")
                .registrationId(Member.RegistrationId.google).build();
        em.persist(member);


        video = Video.builder()
                .thumbnailURL("url")
                .videoName("이름")
                .s3URL("s3")
                .color("색깔")
                .level(3)
                .gymName("암장")
                .isSuccess(true)
                .shootingDate(LocalDate.now())
                .member(member)
                .build();
        em.persist(video);
        videoBoard = VideoBoard.builder()
                .video(video)
                .title("제목")
                .member(member)
                .build();
        em.persist(videoBoard);

        for (int i = 0; i < 5; i++) {
            VideoComment comment = VideoComment.builder()
                    .videoBoard(videoBoard)
                    .content("내용")
                    .member(member)
                    .build();
            em.persist(comment);
        }

        token = TOKEN_PREFIX + jwtTokenUtils.createTokens(member, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    @DisplayName("댓글 전체 조회")
    void getCommentList() throws Exception {
        url += "comment";
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("videoBoardId", String.valueOf(videoBoard.getId()))
                )
                .andReturn()
                .getResponse();

        BaseResponse<List<CommentListResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        Assertions.assertEquals(arrayList.getResult().size(), 5);
    }

    @Test
    @DisplayName("댓글 슬라이싱 전체 조회")
    void getCommentSlice() throws Exception {
        url += "comment";
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("videoBoardId", String.valueOf(videoBoard.getId()))
                                .param("storeId", String.valueOf(3))
                )
                .andDo(print())
                .andReturn()
                .getResponse();

        BaseResponse<List<CommentListResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        Assertions.assertEquals(arrayList.getResult().size(), 2);
    }


    @Test
    @DisplayName("댓글 추가 ")
    void addComment() throws Exception {
        url += "comment";
        NewCommentRequest request = new NewCommentRequest(videoBoard.getId(), "하이하이 새로운 댓글~!");
        mockMvc.perform(
                        post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(request))
                                .header(HttpHeaders.AUTHORIZATION, token)

                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)));
//        url += "comment";
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("videoBoardId", String.valueOf(videoBoard.getId()))
                )
                .andReturn()
                .getResponse();

        BaseResponse<List<CommentListResponse>> arrayList = objectMapper.readValue(response.getContentAsString(), BaseResponse.class);
        Assertions.assertEquals(arrayList.getResult().size(), 6);
    }


    @Test
    @DisplayName("댓글 수정")
    void updateComment() throws Exception {
        url += "comment";
        Long commentId = videoCommentRepository.findAll().get(0).getId();
        CommentUpdateRequest request = new CommentUpdateRequest(commentId, "새로운내용");
        mockMvc.perform(
                        put(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(request))
                                .header(HttpHeaders.AUTHORIZATION, token)

                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)));

        Optional<VideoComment> byId = videoCommentRepository.findById(commentId);
        VideoComment videoComment = byId.orElseThrow();
        String content = videoComment.getContent();
        System.out.println(content);
        Assertions.assertEquals(content, "새로운내용");
    }

    @Test
    @DisplayName("코멘트 삭제")
    void deleteComment() throws Exception {
        url += "comment";
        Long commentId = videoCommentRepository.findAll().get(0).getId();
        mockMvc.perform(
                        delete(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(commentId))
                                .header(HttpHeaders.AUTHORIZATION, token)

                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)));

        Optional<VideoComment> byId = videoCommentRepository.findById(commentId);
        Assertions.assertTrue(byId.isEmpty());
    }

    @Test
    @DisplayName("내꺼 코멘트 조회")
    void getMyCommentList() throws Exception {
        url += "mycomment";
        MockHttpServletResponse response = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, token)

                ).andDo(print())
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn()
                .getResponse();
        BaseResponse<List<MyCommentListResponse>> listBaseResponse = this.convertToBaseResponse(response);
        Assertions.assertEquals(listBaseResponse.getResult().size(), 5);

    }
}
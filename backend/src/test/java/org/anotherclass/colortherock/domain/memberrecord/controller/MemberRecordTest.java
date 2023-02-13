package org.anotherclass.colortherock.domain.memberrecord.controller;

import org.anotherclass.colortherock.IntegrationTest;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils.BEARER_PREFIX;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberRecordTest extends IntegrationTest {

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
    VideoRepository videoRepository;
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    RecordService recordService;
    @Autowired
    MockMvc mockMvc;
    String url = "http://localhost:8080/api";
    Member member;
    String token;
    Video video;
    Long videoId;
    @BeforeEach
    public void setMemberAndToken() {
        // Member 추가 및 token 설정
        member = new Member("johan@rock.com", "조한", Member.RegistrationId.google);
        Member savedMember = memberRepository.save(member);
        token = jwtTokenUtils.createTokens(savedMember, List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
        // 영상 추가
        for (int i = 1; i <= 9; i++) {
            video = UploadVideoRequest.builder()
                    .shootingDate(LocalDate.parse("2023-01-17"))
                    .level(i)
                    .gymName("더클라임 강남")
                    .isSuccess(true)
                    .color("노랑").build().toEntity(savedMember, "s3URL", "thumbURL", "videoName", "thumbName", false);
            Video save = videoRepository.save(video);
            videoId = save.getId();
            video = UploadVideoRequest.builder()
                    .shootingDate(LocalDate.parse("2023-01-17"))
                    .level(i)
                    .gymName("더클라임 홍대")
                    .isSuccess(true)
                    .color("노랑").build().toEntity(savedMember, "s3URL", "thumbURL", "videoName", "thumbName", false);
            videoRepository.save(video);
        }
        // member record 추가
        MemberRecord record = MemberRecord.builder().successCount(18).videoCount(18).member(member).build();
        recordRepository.save(record);
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
                .andExpect(jsonPath("$.result[0].success").value(2));
    }

    @Test
    @DisplayName("[GET]날짜별 운동 기록 색상 통계 조회 - 실패")
    public void 레벨9개_날짜별_통계조회_실패() throws Exception {
        mockMvc.perform(
                        get(url + "/record/color/2023-13-17")
                                .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(jsonPath("$.status", is(400)));
    }

    @Test
    @DisplayName("[GET]전체 운동 기록 누적 통계 조회")
    public void 사용자_누적통계_조회() throws Exception {
        MemberRecord record = recordRepository.findByMember(member);
        int originalVideoCount = record.getVideoCount();
        int originalSuccessCount = record.getSuccessCount();
        recordService.addVideoCount(member, true);
        recordService.addVideoCount(member, true);
        recordService.addVideoCount(member, false);
        mockMvc.perform(
                get(url + "/record/total")
                        .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.result.videoCount").value(originalVideoCount + 3))
                .andExpect(jsonPath("$.result.successCount").value(originalSuccessCount + 2));
    }

    @Test
    @DisplayName("[GET]사용자 날짜별 성공/실패 영상 조회")
    public void 날짜별_성공_실패_영상조회() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("videoId", "-1");
        info.add("shootingDate", "2023-01-17");
        info.add("isSuccess", "true");

        mockMvc.perform(
                        get(url + "/record/videos")
                                .header("Authorization", AUTHORIZATION_HEADER + token)
                                .params(info))
                .andExpect(jsonPath("$.status", is(200)));
    }

    @Test
    @DisplayName("[GET] 영상 상세 조회")
    public void 영상_상세_조회() throws Exception {
        mockMvc.perform(
                        get(url + "/record/video/" + videoId)
                                .header("Authorization", AUTHORIZATION_HEADER + token)
                ).andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.result.level").isNumber());
    }

    @Test
    @DisplayName("[GET] 영상 상세 조회 - 실패(해당 아이디의 영상 없음)")
    public void 영상_상세_조회_실패() throws Exception {
        mockMvc.perform(
                get(url + "/record/video/-1")
                        .header("Authorization", AUTHORIZATION_HEADER + token)
        ).andExpect(jsonPath("$.status", is(404)));
    }

    @Test
    @DisplayName("[POST] 로컬 영상 업로드")
    public void 로컬_영상_업로드_성공() throws Exception {
        MockMultipartFile newVideo = new MockMultipartFile("newVideo", "video.mp4", "mp4", new FileInputStream("src/test/resources/video/test_recording.mp4"));
        String content = "{" +
                "\"shootingDate\": \"2023-01-17\"," +
                "\"level\": 1," +
                "\"color\": \"빨강\"," +
                "\"gymName\": \"볼더프렌즈\"," +
                "\"isSuccess\": true }";
        MockMultipartFile json = new MockMultipartFile("uploadVideoRequest", "jsondata", "application/json", content.getBytes(StandardCharsets.UTF_8));
        mockMvc.perform(multipart(url + "/record/video")
                    .file(newVideo)
                    .file(json)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(jsonPath("$.status", is(200)));

    }

    @Test
    @DisplayName("[DELETE] 영상 삭제 요청")
    public void 영상_삭제() throws Exception {
        mockMvc.perform(
                delete(url + "/record/video/" + videoId)
                        .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(jsonPath("$.status", is(200)));
    }

    @Test
    @DisplayName("[GET] 암장 방문 통계 반환")
    public void 통계반환_성공() throws Exception {
        video = UploadVideoRequest.builder()
                .shootingDate(LocalDate.parse("2023-01-18"))
                .level(1)
                .gymName("더클라임 강남")
                .isSuccess(true)
                .color("노랑").build().toEntity(member);
        videoRepository.save(video);
        mockMvc.perform(
                        get(url + "/record/visit")
                                .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.result.totalCount").isNumber())
                .andExpect(jsonPath("$.result.data").isArray())
                .andExpect(jsonPath("$.result.data[0].gymName", is("더클라임 강남")))
                .andExpect(jsonPath("$.result.data[0].count", is(2)));
    }

    @Test
    @DisplayName("[GET] 날짜별 완등 레벨 색상 반환")
    public void 레벨색상반환_성공() throws Exception {
        video = UploadVideoRequest.builder()
                .shootingDate(LocalDate.parse("2023-01-18"))
                .level(1)
                .gymName("더클라임 강남")
                .isSuccess(true)
                .color("노랑").build().toEntity(member);
        videoRepository.save(video);
        mockMvc.perform(
                get(url + "/record/calendar/2023-01")
                        .header("Authorization", AUTHORIZATION_HEADER + token))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[0].date", is("2023-01-17")));
    }

}

package org.anotherclass.colortherock.domain.report.service;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.report.entity.Report;
import org.anotherclass.colortherock.domain.report.request.PostReportRequest;
import org.anotherclass.colortherock.domain.report.request.PostUnhiddenRequest;
import org.anotherclass.colortherock.domain.report.response.AdminReportDetailResponse;
import org.anotherclass.colortherock.domain.report.response.AdminReportedPostResponse;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.global.error.GlobalBaseException;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AdminReportServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoBoardRepository videoBoardRepository;
    @Autowired
    private ReportService reportService;
    @Autowired
    private AdminReportService adminReportService;

    private ArrayList<Long> memberIds;
    private ArrayList<Long> videoBoardIds;

    @BeforeEach
    void setData() {
        memberIds = new ArrayList<>();
        videoBoardIds = new ArrayList<>();
        // Member, Video, VideoBoard ??????
        for (int i = 0; i < 10; i++) {
            Member member = new Member(i + "@rock.com", i + "user", Member.RegistrationId.kakao);
            memberRepository.save(member);
            memberIds.add(member.getId());
            Video video = Video.builder()
                    .shootingDate(LocalDate.parse("2022-01-30"))
                    .level(1)
                    .gymName("???????????????")
                    .s3URL("url")
                    .videoName("name")
                    .isSuccess(true)
                    .thumbnailURL("url")
                    .thumbnailName("name")
                    .color("??????")
                    .member(member)
                    .build();
            videoRepository.save(video);
            VideoBoard videoBoard = VideoBoard.builder()
                    .title("??? ?????????")
                    .isHidden(false)
                    .video(video)
                    .member(member)
                    .build();
            videoBoardRepository.save(videoBoard);
            videoBoardIds.add(videoBoard.getId());
        }

        // ?????? ??????
        for (int i = 1; i < 6; i++) {
            Member member = memberRepository.findById(memberIds.get(i))
                    .orElseThrow(() -> new GlobalBaseException(GlobalErrorCode.USER_NOT_FOUND));
            for (int j = 0; j < 2; j++) {
                reportService.reportPost(member, new PostReportRequest(videoBoardIds.get(0), "TYPE_A"));
            }
        }
    }

    @Test
    @DisplayName("[?????????] ??????????????? ?????? ????????? ????????????")
    void getReportedPostByAdmin() {
        // when
        List<AdminReportedPostResponse> result = adminReportService.getReportedVideoBoard();
        // then
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("[?????????] ????????? ???????????? ?????? ?????? ?????? ?????? ????????????")
    void getReportedPostDetail() {
        Long videoBoardId = videoBoardIds.get(0);
        List<AdminReportDetailResponse> result = adminReportService.getReportDetail(videoBoardId);
        // then
        assertEquals(10, result.size());
        assertEquals(Report.Category.TYPE_A.getValue(), String.valueOf(result.get(0).getReportContent()));
    }

    @Test
    @DisplayName("[?????????] ??????????????? ?????? ?????? ?????? ??? ?????? ?????? ??????")
    void cancelHiddenStatus() {
        // given
        VideoBoard videoBoard = videoBoardRepository.findById(videoBoardIds.get(0))
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        // ?????? ????????? ???????????? ??????
        assertEquals(true, videoBoard.getIsHidden());
        // when
        PostUnhiddenRequest request = new PostUnhiddenRequest(videoBoard.getId());
        adminReportService.cancelHiddenStatus(request);
        List<AdminReportDetailResponse> reportList = adminReportService.getReportDetail(videoBoard.getId());
        // then
        assertEquals(false, videoBoard.getIsHidden());
        assertEquals(0, reportList.size());
    }

    @Test
    @DisplayName("[?????????] ?????? ?????? ??????")
    void deleteReportedVideo() {
        // given
        Long videoBoardId = videoBoardIds.get(0);
        // when
        adminReportService.deleteReportedVideo(videoBoardId);
        // then
        assertThrows(PostNotFoundException.class, () -> adminReportService.deleteReportedVideo(videoBoardId));
    }

}
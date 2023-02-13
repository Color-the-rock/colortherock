package org.anotherclass.colortherock.test;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.report.request.PostReportRequest;
import org.anotherclass.colortherock.domain.report.service.ReportService;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.domain.videocomment.entity.VideoComment;
import org.anotherclass.colortherock.domain.videocomment.repository.VideoCommentRepository;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.security.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequiredArgsConstructor
public class InsertMockData {
    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;
    private final VideoBoardRepository videoBoardRepository;
    private final VideoCommentRepository videoCommentRepository;
    private final ReportService reportService;

    private final JwtTokenUtils jwtTokenUtils;

    private List<TokensResponse> response;

    @Value("${CLOUDFRONT_URL}")
    private String cloudFrontUrl;
    private final String videoName1 = "20230203_091428077.mp4";
    private final String videoName2 = "KakaoTalk_20230203_101632874.mp4";
    private final String thumbnail1 = "thumbnail1.png";
    private final String thumbnail2 = "thumbnail2.jpg";

    private List<Member> members;
    private final RecordRepository recordRepository;


    @PostConstruct
    private void insertData() {
        // Member
        Member member1 = Member.builder().email("example1@colortherock.com")
                .nickname("닉네임1").registrationId(Member.RegistrationId.kakao).build();
        Member member2 = Member.builder().email("example2@colortherock.com")
                .nickname("닉네임2").registrationId(Member.RegistrationId.google).build();
        Member member3 = Member.builder().email("example3@colortherock.com")
                .nickname("닉네임3").registrationId(Member.RegistrationId.google).build();
        Member member4 = Member.builder().email("example4@colortherock.com")
                .nickname("닉네임4").registrationId(Member.RegistrationId.google).build();
        Member member5 = Member.builder().email("example5@colortherock.com")
                .nickname("닉네임5").registrationId(Member.RegistrationId.google).build();
        Member member6 = Member.builder().email("example6@colortherock.com")
                .nickname("닉네임6").registrationId(Member.RegistrationId.google).build();

        members = Arrays.asList(
                member1, member2, member3, member4, member5, member6
        );
        memberRepository.saveAll(members);


        // Video
        List<Long> videoNums = new ArrayList<>();
        List<Video> videos = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            videos.add(Video.builder().videoName(videoName1).level(4).isSuccess(true).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-08")).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("빨강").build());
            videos.add(Video.builder().videoName(videoName1).level(2).isSuccess(true).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-08")).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("노랑").build());
        }

        videos.add(Video.builder().videoName(videoName1).level(2).isSuccess(true).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-08")).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("빨강").build());
        for (int i = 1; i < 10; i++) {
            videos.add(Video.builder().videoName(videoName1).level(i).isSuccess(true).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-1" + (i))).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("노랑").build());
            videos.add(Video.builder().videoName(videoName1).level(i).isSuccess(false).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1" + (i))).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("주황").build());
            videos.add(Video.builder().videoName(videoName1).level(i).isSuccess(true).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1" + (i))).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("초록").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(false).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-1" + (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("파랑").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(true).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-1" + (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("남색").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(false).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1" + (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("보라").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(true).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1" + (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("갈색").build());
        }
        videos.add(Video.builder().videoName(videoName2).level(2).isSuccess(true).gymName("더클라임 홍대").member(member1).shootingDate(LocalDate.parse("2023-01-20")).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("빨강").build());
        videos.add(Video.builder().videoName(videoName2).level(5).isSuccess(true).gymName("더클라임 홍대").member(member1).shootingDate(LocalDate.parse("2023-01-24")).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("초록").build());
        videos.add(Video.builder().videoName(videoName2).level(3).isSuccess(true).gymName("더클라임 강남").member(member2).shootingDate(LocalDate.parse("2023-01-20")).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("노랑").build());
        videoRepository.saveAll(videos);
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getIsSuccess()) videoNums.add(videos.get(i).getId());
        }

        // VideoBoard
        List<VideoBoard> videoBoards = new ArrayList<>();
        for (int i = 0; i < videoNums.size(); i++) {
            Video video = videoRepository.findById(videoNums.get(i)).orElseThrow();
            videoBoards.add(VideoBoard.builder().video(video).title(i + "번째 테스트 제목입니다. 화이팅~~~~~~!")
                    .isHidden(false).member(video.getMember()).build());
        }
        videoBoardRepository.saveAll(videoBoards);

        // Report
        for (int i = 0; i < 2; i++) {
            Long videoBoardId = videoBoards.get(i).getId();
            PostReportRequest request = new PostReportRequest(videoBoardId, "TYPE_A");
            reportService.reportPost(member2, request);
            reportService.reportPost(member3, request);
            reportService.reportPost(member4, request);
            reportService.reportPost(member5, request);
            reportService.reportPost(member6, request);
        }

        // Comment
        List<VideoComment> comments = new ArrayList<>();
        for (int i = 0; i < videoBoards.size(); i++) {
            comments.add(VideoComment.builder().content("잘 하시네요11").videoBoard(videoBoards.get(i)).member(member1).build());
            comments.add(VideoComment.builder().content("멋있으시네요22").videoBoard(videoBoards.get(i)).member(member2).build());
        }
        videoCommentRepository.saveAll(comments);

        // MemberRecord
        List<MemberRecord> memberRecords = Arrays.asList(
                MemberRecord.builder()
                        .member(member1).videoCount(30).successCount(21).build(),
                MemberRecord.builder()
                        .member(member2).videoCount(37).successCount(19).build()
        );
        recordRepository.saveAll(memberRecords);
    }

    @GetMapping("/api/test/tokens")
    public BaseResponse<List<TokensResponse>> getTestTokens() {
        String token1 = jwtTokenUtils.createTokens(members.get(0), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        String token2 = jwtTokenUtils.createTokens(members.get(1), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        response = new ArrayList<>();
        response.add(TokensResponse.builder().token(token1).nickname(members.get(0).getNickname()).build());
        response.add(TokensResponse.builder().token(token2).nickname(members.get(1).getNickname()).build());
        return new BaseResponse<>(response);
    }
}

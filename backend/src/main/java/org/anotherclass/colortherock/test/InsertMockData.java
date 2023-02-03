package org.anotherclass.colortherock.test;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
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

    private final JwtTokenUtils jwtTokenUtils;

    private List<TokensResponse> response;

    @Value("${CLOUDFRONT_URL}") private String cloudFrontUrl;
    private final String videoName1 = "20230203_091428077.mp4";
    private final String videoName2 = "KakaoTalk_20230203_101632874.mp4";
    private final String thumbnail1 = "thumbnail1.png";
    private final String thumbnail2 = "thumbnail2.jpg";



    @PostConstruct
    private void insertData() {
        // Member
        Member member1 = Member.builder().email("example1@colortherock.com")
                .nickname("닉네임1").registrationId(Member.RegistrationId.kakao).build();
        Member member2 = Member.builder().email("example2@colortherock.com")
                .nickname("닉네임2").registrationId(Member.RegistrationId.google).build();
        List<Member> members = Arrays.asList(
            member1, member2
        );
        memberRepository.saveAll(members);
        String token1 = jwtTokenUtils.createTokens(member1, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        String token2 = jwtTokenUtils.createTokens(member1, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        response = new ArrayList<>();
        response.add(TokensResponse.builder().token(token1).nickname(member1.getNickname()).build());
        response.add(TokensResponse.builder().token(token2).nickname(member2.getNickname()).build());

        // Video
        List<Long> videoNums = new ArrayList<>();
        List<Video> videos = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            videos.add(Video.builder().videoName(videoName1).level(i).isSuccess(false).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("빨강").build());
            videos.add(Video.builder().videoName(videoName1).level(i).isSuccess(true).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("노랑").build());
            videos.add(Video.builder().videoName(videoName1).level(i).isSuccess(false).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("주황").build());
            videos.add(Video.builder().videoName(videoName1).level(i).isSuccess(true).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName1).thumbnailURL(cloudFrontUrl + thumbnail1).color("초록").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(false).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("파랑").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(true).gymName("더클라임 강남").member(member1).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("남색").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(false).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("보라").build());
            videos.add(Video.builder().videoName(videoName2).level(i).isSuccess(true).gymName("더클라임 홍대").member(member2).shootingDate(LocalDate.parse("2023-01-1"+ (i))).s3URL(cloudFrontUrl + videoName2).thumbnailURL(cloudFrontUrl + thumbnail2).color("갈색").build());
        }
        videoRepository.saveAll(videos);
        for (int i = 0; i < videos.size(); i++) {
            if(videos.get(i).getIsSuccess()) videoNums.add(videos.get(i).getId());
        }

        // VideoBoard
        List<VideoBoard> videoBoards = new ArrayList<>();
        for (int i = 0; i < videoNums.size(); i++) {
            Video video = videoRepository.findById(videoNums.get(i)).orElseThrow();
            videoBoards.add(VideoBoard.builder().video(video).title("테스트 제목입니다.").member(video.getMember()).build());
        }
        videoBoardRepository.saveAll(videoBoards);

        // Comment
        List<VideoComment> comments = new ArrayList<>();
        for (int i = 0; i < videoBoards.size(); i++) {
            comments.add(VideoComment.builder().content("잘 하시네요11").videoBoard(videoBoards.get(i)).member(member1).build());
            comments.add(VideoComment.builder().content("멋있으시네요22").videoBoard(videoBoards.get(i)).member(member2).build());
        }
        videoCommentRepository.saveAll(comments);

    }

    @GetMapping("/api/test/tokens")
    public BaseResponse<List<TokensResponse>> getTestTokens() {
        return new BaseResponse<>(response);
    }
}

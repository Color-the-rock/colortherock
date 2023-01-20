package org.anotherclass.colortherock.domain.memberrecord.controller;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.memberrecord.exception.MalformedDateException;
import org.anotherclass.colortherock.domain.memberrecord.response.LevelStatResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.TotalStatResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoDetailResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoListResponse;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.anotherclass.colortherock.domain.video.service.S3Service;
import org.anotherclass.colortherock.domain.video.service.VideoService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/record")
public class RecordController {

    private final RecordService recordService;
    private final S3Service s3Service;
    private final VideoService videoService;

    /**
     * 전체 운동 영상 색상 별 통계 조회
     */
    @GetMapping("/color")
    public BaseResponse<List<LevelStatResponse>> recordsByColor(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        List<LevelStatResponse> colorRecords = recordService.getColorRecords(member);
        return new BaseResponse<>(colorRecords);
    }

    /**
     * 날짜별 운동 기록 색상 별 통계 조회
     */
    @GetMapping("/color/{date}")
    public BaseResponse<List<LevelStatResponse>> recordsByColorAndDate(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String date) throws MalformedDateException {
        Member member = memberDetails.getMember();
        // 날짜 형식이 YYYY-MM-DD 이 아닌 경우 예외 발생
        if(!date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
            throw new MalformedDateException(GlobalErrorCode.MALFORMED_DATE);
        }
        LocalDate videoDate = LocalDate.parse(date);
        List<LevelStatResponse> dateRecords = recordService.getDateRecords(member, videoDate);
        return new BaseResponse<>(dateRecords);
    }

    /**
     * 전체 운동 기록 누적 통계 조회
     */
    @GetMapping("/total")
    public BaseResponse<TotalStatResponse> recordsTotal(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        TotalStatResponse totalStatResponse = recordService.getTotalRecords(member);
        return new BaseResponse<>(totalStatResponse);
    }

    /**
     * 날짜별 운동 영상 목록 조회(성공 영상)
     */
    @GetMapping("/videos/success/{date}")
    public BaseResponse<List<VideoListResponse>> successVideosByDate(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String date) {
        Member member = memberDetails.getMember();
        // 날짜 형식이 YYYY-MM-DD 이 아닌 경우 예외 발생
        if(!date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
            throw new MalformedDateException(GlobalErrorCode.MALFORMED_DATE);
        }
        LocalDate videoDate = LocalDate.parse(date);
        List<VideoListResponse> successResponse = recordService.getSuccessVideos(member, videoDate);
        return new BaseResponse<>(successResponse);
    }

    /**
     * 날짜별 운동 영상 목록 조회(실패 영상)
     */
    @GetMapping("/videos/fail/{date}")
    public BaseResponse<List<VideoListResponse>> failVideosByDate(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String date) {
        Member member = memberDetails.getMember();
        // 날짜 형식이 YYYY-MM-DD 이 아닌 경우 예외 발생
        if(!date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
            throw new MalformedDateException(GlobalErrorCode.MALFORMED_DATE);
        }
        LocalDate videoDate = LocalDate.parse(date);
        List<VideoListResponse> failResponses = recordService.getFailVideos(member, videoDate);
        return new BaseResponse<>(failResponses);
    }

    /**
     * 영상 재생을 위한 영상 상세 조회
     */
    @GetMapping("/video/{id}")
    public BaseResponse<VideoDetailResponse> videoDetail(@PathVariable @NotNull @Min(value=0, message="videoId는 0이상의 정수입니다.") Long id) {
        VideoDetailResponse videoDetail = recordService.getVideoDetail(id);
        return new BaseResponse<>(videoDetail);
    }

    /**
     * 개인 로컬 영상 업로드
     * @param uploadVideoRequest 업로드 영상 50MB 이상 시, 예외 발생
     */
    @PostMapping("/video")
    public BaseResponse uploadVideo(@AuthenticationPrincipal MemberDetails memberDetails, @Valid UploadVideoRequest uploadVideoRequest) throws IOException {
        Member member = memberDetails.getMember();
        // S3 영상 저장 후 URL 얻어오기
        MultipartFile newVideo = uploadVideoRequest.getNewVideo();
        String s3URL = s3Service.upload(newVideo);
        // request와 URL을 통해 DB에 저장
        videoService.uploadVideo(member, s3URL, uploadVideoRequest);
        return new BaseResponse(GlobalErrorCode.SUCCESS);
    }
}

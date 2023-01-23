package org.anotherclass.colortherock.domain.memberrecord.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.joda.time.DateTime;
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
@Tag(name = "member record", description = "Member Record API")
@RequiredArgsConstructor
@RequestMapping("/api/record")
public class RecordController {

    private final RecordService recordService;
    private final S3Service s3Service;
    private final VideoService videoService;

    /**
     * 전체 운동 영상 색상 별 통계 조회
     */
    @Operation(description = "사용자별 전체 운동 영상 색상 별 통계 조회")
    @ApiResponse(responseCode = "200", description = "전체 통계 조회 성공", content = @Content(schema = @Schema(implementation = LevelStatResponse.class)))
    @GetMapping("/color")
    public BaseResponse<List<LevelStatResponse>> recordsByColor(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        List<LevelStatResponse> colorRecords = recordService.getColorRecords(member);
        return new BaseResponse<>(colorRecords);
    }

    /**
     * 날짜별 운동 기록 색상 별 통계 조회
     */
    @Operation(description = "사용자별 선택 날짜에 대한 운동 영상 색상 별 통계 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "전체 통계 조회 성공", content = @Content(schema = @Schema(implementation = LevelStatResponse.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 날짜 형식으로 인한 통계 조회 실패")
    })
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
    @Operation(description = "사용자별 전체 운동 기록 누적 통계 조회")
    @ApiResponse(responseCode = "200", description = "전체 운동 누적 통계 조회 성공", content = @Content(schema = @Schema(implementation = TotalStatResponse.class)))
    @GetMapping("/total")
    public BaseResponse<TotalStatResponse> recordsTotal(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        TotalStatResponse totalStatResponse = recordService.getTotalRecords(member);
        return new BaseResponse<>(totalStatResponse);
    }

    /**
     * 날짜별 운동 영상 목록 조회(성공 영상)
     */
    @Operation(description = "사용자별 날짜별 성공 영상 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "해당 날짜 영상 조회 성공", content = @Content(schema = @Schema(implementation = VideoListResponse.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 날짜 형식으로 인한 영상 조회 실패")
    })
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
    @Operation(description = "사용자별 날짜별 실패 영상 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "해당 날짜 영상 조회 성공", content = @Content(schema = @Schema(implementation = VideoListResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 날짜 형식으로 인한 영상 조회 실패")
    })
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
    @Operation(description = "영상 재생을 위한 영상 상세 정보 조회")
    @ApiResponse(responseCode = "200", description = "영상 정보 조회 성공", content = @Content(schema = @Schema(implementation = VideoDetailResponse.class)))
    @GetMapping("/video/{id}")
    public BaseResponse<VideoDetailResponse> videoDetail(@PathVariable @NotNull @Min(value=0, message="videoId는 0이상의 정수입니다.") Long id) {
        VideoDetailResponse videoDetail = recordService.getVideoDetail(id);
        return new BaseResponse<>(videoDetail);
    }

    /**
     * 개인 로컬 영상 업로드
     * @param uploadVideoRequest 업로드 영상 50MB 이상 시, 예외 발생
     */
    @Operation(description = "로컬 영상 개인 기록용 업로드")
    @ApiResponse(responseCode = "200", description = "영상 업로드 성공")
    @PostMapping("/video")
    public BaseResponse<Void> uploadVideo(@AuthenticationPrincipal MemberDetails memberDetails, @Valid UploadVideoRequest uploadVideoRequest) throws IOException {
        Member member = memberDetails.getMember();
        // S3 영상 저장 후 URL 얻어오기
        MultipartFile newVideo = uploadVideoRequest.getNewVideo();
        // 영상 식별을 위해 파일 앞에 현재 시각 추가
        String videoName = DateTime.now() + newVideo.getOriginalFilename();
        String s3URL = s3Service.upload(newVideo, videoName);
        uploadVideoRequest.setVideoName(videoName);
        // request와 URL을 통해 DB에 저장
        videoService.uploadVideo(member, s3URL, uploadVideoRequest);
        // 영상의 길이를 사용자별 누적 기록에 추가
        // 영상 길이를 확인하는 방법 고민 중..
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }
}

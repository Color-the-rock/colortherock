package org.anotherclass.colortherock.domain.memberrecord.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.memberrecord.exception.MalformedDateException;
import org.anotherclass.colortherock.domain.memberrecord.response.*;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
import org.anotherclass.colortherock.domain.video.dto.DeletedVideoDto;
import org.anotherclass.colortherock.domain.video.request.MyVideoRequest;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.anotherclass.colortherock.domain.video.service.S3Service;
import org.anotherclass.colortherock.domain.video.service.VideoService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.security.annotation.PreAuthorizeMember;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "member record", description = "Member Record API")
@RequiredArgsConstructor
@RequestMapping("/api/record")
@Slf4j
public class RecordController {

    private final RecordService recordService;
    private final S3Service s3Service;
    private final VideoService videoService;

    /**
     * 전체 운동 영상 색상 별 통계 조회
     */
    @GetMapping("/color")
    @Operation(description = "사용자별 전체 운동 영상 색상 별 통계 조회", summary = "사용자별 운동 색상 별 통계 조회")
    @ApiResponse(responseCode = "200", description = "전체 통계 조회 성공", content = @Content(schema = @Schema(implementation = LevelStatResponse.class)))
    @PreAuthorizeMember
    public BaseResponse<List<LevelStatResponse>> recordsByColor(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        List<LevelStatResponse> colorRecords = recordService.getColorRecords(member);
        return new BaseResponse<>(colorRecords);
    }

    /**
     * 날짜별 운동 기록 색상 별 통계 조회
     */
    @GetMapping("/color/{date}")
    @Operation(description = "사용자별 선택 날짜에 대한 운동 영상 색상 별 통계 조회", summary = "사용자별 선택 날짜에 대한 운동 영상 색상 별 통계 조회")
    @ApiResponse(responseCode = "200", description = "전체 통계 조회 성공", content = @Content(schema = @Schema(implementation = LevelStatResponse.class)))
    @ApiResponse(responseCode = "400", description = "잘못된 날짜 형식으로 인한 통계 조회 실패")
    @PreAuthorizeMember
    public BaseResponse<List<LevelStatResponse>> recordsByColorAndDate(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String date) throws MalformedDateException {
        Member member = memberDetails.getMember();
        // 날짜 형식이 YYYY-MM-DD 이 아닌 경우 예외 발생
        if (!date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
            throw new MalformedDateException(GlobalErrorCode.MALFORMED_DATE);
        }
        LocalDate videoDate = LocalDate.parse(date);
        log.info("{}", videoDate);
        List<LevelStatResponse> dateRecords = recordService.getDateRecords(member, videoDate);
        return new BaseResponse<>(dateRecords);
    }

    /**
     * 전체 운동 기록 누적 통계 조회
     */
    @GetMapping("/total")
    @Operation(description = "사용자별 전체 운동 기록 누적 통계 조회", summary = "사용자별 전체 운동 기록 누적 통계 조회")
    @ApiResponse(responseCode = "200", description = "전체 운동 누적 통계 조회 성공", content = @Content(schema = @Schema(implementation = TotalStatResponse.class)))
    @PreAuthorizeMember
    public BaseResponse<TotalStatResponse> recordsTotal(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        TotalStatResponse totalStatResponse = recordService.getTotalRecords(member);
        return new BaseResponse<>(totalStatResponse);
    }

    /**
     * 날짜별 운동 영상 목록 조회(성공 / 실패 영상)
     */
    @GetMapping("/videos")
    @Operation(description = "사용자별 날짜별 성공/실패 영상 조회", summary = "사용자별 날짜별 성공/실패 영상 조회")
    @ApiResponse(responseCode = "200", description = "해당 날짜 영상 조회 성공", content = @Content(schema = @Schema(implementation = VideoListResponse.class)))
    @ApiResponse(responseCode = "500", description = "잘못된 날짜 형식으로 인한 영상 조회 실패")
    @PreAuthorizeMember
    public BaseResponse<List<VideoListResponse>> MyVideosByDate(@AuthenticationPrincipal MemberDetails memberDetails, @Valid MyVideoRequest myVideoRequest) {
        Member member = memberDetails.getMember();
        log.info("{}",myVideoRequest.getShootingDate());
        List<VideoListResponse> videoListResponses = recordService.getMyVideos(member, myVideoRequest);
        return new BaseResponse<>(videoListResponses);
    }


    /**
     * 영상 재생을 위한 영상 상세 조회
     */
    @GetMapping("/video/{id}")
    @Operation(description = "영상 재생을 위한 영상 상세 정보 조회", summary = "영상 재생을 위한 영상 상세 정보 조회")
    @ApiResponse(responseCode = "200", description = "영상 정보 조회 성공", content = @Content(schema = @Schema(implementation = VideoDetailResponse.class)))
    public BaseResponse<VideoDetailResponse> videoDetail(@PathVariable @NotNull @Min(value = 0, message = "videoId는 0이상의 정수입니다.") Long id) {
        VideoDetailResponse videoDetail = recordService.getVideoDetail(id);
        return new BaseResponse<>(videoDetail);
    }

    /**
     * 개인 로컬 영상 업로드
     *
     * @param uploadVideoRequest 업로드 영상 100MB 이상 시, 예외 발생
     */
    @PostMapping(value = "/video", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "로컬 영상 개인 기록용 업로드", summary = "로컬 영상 개인 기록용 업로드")
    @ApiResponse(responseCode = "200", description = "영상 업로드 성공")
    @PreAuthorizeMember
    public BaseResponse<Void> uploadVideo(@AuthenticationPrincipal MemberDetails memberDetails
            , @Valid @RequestPart UploadVideoRequest uploadVideoRequest, @RequestPart MultipartFile newVideo) {
        videoService.uploadMyVideo(memberDetails, newVideo, uploadVideoRequest);
        log.info("{}", uploadVideoRequest.getShootingDate());
        // 영상 누적 통계에서 영상 갯수 올리기
        Member member = memberDetails.getMember();
        recordService.addVideoCount(member, uploadVideoRequest.getIsSuccess());
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    /**
     * 개인 영상 기록 삭제 요청
     *
     * @param memberDetails JWT를 통한 Member 불러오기
     * @param videoId       삭제하고자 하는 videoId
     */
    @DeleteMapping("/video/{videoId}")
    @Operation(description = "개인 영상 기록 삭제 요청", summary = "개인 영상 기록 삭제 요청")
    @ApiResponse(responseCode = "200", description = "개인 영상 기록 삭제 성공")
    @ApiResponse(responseCode = "400", description = "해당 videoId에 맞는 Video가 존재하지 않음")
    @PreAuthorizeMember
    public BaseResponse<Void> deleteVideo(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable @Positive Long videoId) {
        Member member = memberDetails.getMember();
        // DB에서 해당 영상 삭제
        DeletedVideoDto deletedVideoDto = videoService.deleteVideo(member, videoId);
        // S3에서 해당 영상 삭제
        s3Service.deleteFile(deletedVideoDto.getVideoName());
        // 영상 누적 통계에서 영상 갯수 줄이기
        recordService.subVideoCount(member, deletedVideoDto.getIsVideoSuccess());
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    /**
     * 암장 방문 통계 반환 메소드
     *
     * @param memberDetails JWT 토큰을 통한 memberId 조회
     * @return VisitListResponse의 List형태로 반환
     */
    @GetMapping("/visit")
    @Operation(description = "암장 방문 통계 반환 메소드", summary = "암장 방문 통계 반환 메소드")
    @ApiResponse(responseCode = "200", description = "방문 통계 반환 성공", content = @Content(schema = @Schema(implementation = VisitListDto.class)))
    @PreAuthorizeMember
    public BaseResponse<VisitResponse> getVisitList(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        VisitResponse visitResponse = recordService.getVisitList(member);
        return new BaseResponse<>(visitResponse);
    }

    /**
     * 해당 월의 각 날짜에 대한 완등 영상 상위 레벨 3개의 색상 반환
     *
     * @param memberDetails JWT 토큰을 통한 memberId 조회
     * @param yearMonth     조회할 연도와 월을 입력
     * @return 완등 영상이 있는 날짜에 대해 DailyColorResponse를 List형태로 반환
     */
    @GetMapping("/calendar/{yearMonth}")
    @Operation(description = "운동 기록 캘린더 색상 반환", summary = "운동 기록 캘린더 색상 반환")
    @ApiResponse(responseCode = "200", description = "색상 반환 성공", content = @Content(schema = @Schema(implementation = DailyColorResponse.class)))
    @ApiResponse(responseCode = "400", description = "잘못된 날짜 형식으로 인한 조회 실패 YYYY-MM 형태 입력 필요")
    @PreAuthorizeMember
    public BaseResponse<List<DailyColorResponse>> getCalendarColor(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String yearMonth) {
        if (!yearMonth.matches("\\d{4}-(0[1-9]|1[012])")) {
            throw new MalformedDateException(GlobalErrorCode.MALFORMED_DATE);
        }
        log.info("{}", yearMonth);
        Member member = memberDetails.getMember();
        List<DailyColorResponse> calendarColor = recordService.getCalendarColor(member, yearMonth);
        return new BaseResponse<>(calendarColor);
    }
}

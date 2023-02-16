package org.anotherclass.colortherock.domain.videoboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoListResponse;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
import org.anotherclass.colortherock.domain.video.request.MySuccessVideoRequest;
import org.anotherclass.colortherock.domain.video.service.VideoService;
import org.anotherclass.colortherock.domain.videoboard.request.LocalSuccessVideoUploadRequest;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessPostUpdateRequest;
import org.anotherclass.colortherock.domain.videoboard.request.SuccessVideoUploadRequest;
import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardDetailResponse;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
import org.anotherclass.colortherock.domain.videoboard.service.VideoBoardService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.anotherclass.colortherock.global.security.annotation.PreAuthorizeMember;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/video")
public class VideoBoardController {
    private final VideoBoardService videoBoardService;
    private final VideoService videoService;
    private final RecordService recordService;

    @Operation(description = "완등 영상 전체 리스트 조회 API", summary = "완등 영상 전체 리스트 조회 API")
    @ApiResponse(responseCode = "200", description = "완등 영상 목록 조회 성공", content = @Content(schema = @Schema(implementation = VideoBoardSummaryResponse.class)))
    @GetMapping("/board")
    public BaseResponse<List<VideoBoardSummaryResponse>> getVideoList
            (VideoBoardSearchRequest condition) {


        List<VideoBoardSummaryResponse> successVideoList = videoBoardService.getSuccessVideos(condition);

        return new BaseResponse<>(successVideoList);
    }

    @Operation(description = "완등 영상 게시글 올리기(로컬 파일에서 영상 가져오기)", summary = "완등 영상 게시글 올리기(로컬 파일에서 영상 가져오기)")
    @ApiResponse(responseCode = "200", description = "운동 영상 올리기 성공", content = @Content(schema = @Schema(implementation = Long.class)))
    @PreAuthorizeMember
    @PostMapping(value = "/board/local", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Long> uploadSuccessPostFromLocalVideo(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @RequestPart LocalSuccessVideoUploadRequest localSuccessVideoUploadRequest, @RequestPart MultipartFile newVideo) {
        Long videoId = videoService.uploadSuccessVideo(memberDetails, newVideo, localSuccessVideoUploadRequest);
        log.info("{}" , localSuccessVideoUploadRequest.getShootingTime());
        // 운동 게시글 업로드
        SuccessVideoUploadRequest request = SuccessVideoUploadRequest.builder()
                .title(localSuccessVideoUploadRequest.getTitle())
                .videoId(videoId)
                .build();
        Member member = memberDetails.getMember();
        Long videoBoardId = videoBoardService.uploadMySuccessVideoPost(member.getId(), request);
        // 운동기록 통계 카운트 증가
        recordService.addVideoCount(member, true);
        return new BaseResponse<>(videoBoardId);
    }

    @Operation(description = "영상 게시글 업로드용 영상 목록 가져오기", summary = "영상 게시글 업로드용 영상 목록 가져오기")
    @ApiResponse(responseCode = "200", description = "성공 영상 불러오기 성공", content = @Content(schema = @Schema(implementation = VideoListResponse.class)))
    @PreAuthorizeMember
    @GetMapping("/board/myvideo")
    public BaseResponse<List<VideoListResponse>> getMySuccessVideoList(@AuthenticationPrincipal MemberDetails memberDetails, @Valid MySuccessVideoRequest request) {
        Member member = memberDetails.getMember();
        log.info("{}" , request.getShootingDate());
        List<VideoListResponse> mySuccessVideoList = videoService.getMySuccessVideoList(member, request);
        return new BaseResponse<>(mySuccessVideoList);
    }

    @Operation(description = "완등 영상 게시글 올리기(내 운동기록 동영상에서 영상 가져오기)", summary = "완등 영상 게시글 올리기(내 운동기록 동영상에서 영상 가져오기)")
    @ApiResponse(responseCode = "200", description = "운동 영상 올리기 성공", content = @Content(schema = @Schema(implementation = Long.class)))
    @ApiResponse(responseCode = "404", description = "유저 정보를 찾을 수 없음")
    @ApiResponse(responseCode = "404", description = "해당하는 영상을 찾을 수 없음")
    @PreAuthorizeMember
    @PostMapping("/board")
    public BaseResponse<Long> uploadSuccessPostFromMyVideo(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @RequestBody SuccessVideoUploadRequest successVideoUploadRequest) {
        Member member = memberDetails.getMember();
        Long videoBoardId = videoBoardService.uploadMySuccessVideoPost(member.getId(), successVideoUploadRequest);
        return new BaseResponse<>(videoBoardId);
    }

    @Operation(description = "완등 영상 게시글 상세보기 API", summary = "완등 영상 게시글 상세보기 API")
    @ApiResponse(responseCode = "200", description = "완등 영상 상세 조회 성공", content = @Content(schema = @Schema(implementation = VideoBoardDetailResponse.class)))
    @ApiResponse(responseCode = "404", description = "해당하는 영상 게시글을 찾을 수 없음")
    @GetMapping("/board/detail")
    public BaseResponse<VideoBoardDetailResponse> getVideoDetail(@NotNull @RequestParam(required = false) Long videoBoardId) {

        VideoBoardDetailResponse videoDetail = videoBoardService.getVideoDetail(videoBoardId);
        return new BaseResponse<>(videoDetail);
    }

    @Operation(description = "완등 영상 게시글 수정하기 API", summary = "완등 영상 게시글 수정하기 API")
    @ApiResponse(responseCode = "200", description = "완등 영상 게시글 수정 성공")
    @ApiResponse(responseCode = "404", description = "해당하는 영상 게시글을 찾을 수 없음")
    @ApiResponse(responseCode = "404", description = "작성자와 유저 정보가 일치하지 않음")
    @PreAuthorizeMember

    @PutMapping("/board/detail")
    public BaseResponse<Object> updateSuccessPost(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @RequestBody SuccessPostUpdateRequest successPostUpdateRequest) {
        Member member = memberDetails.getMember();
        videoBoardService.updateSuccessPost(member.getId(), successPostUpdateRequest);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @Operation(description = "완등 영상 게시글 삭제하기 API", summary = "완등 영상 게시글 삭제하기 API")
    @ApiResponse(responseCode = "200", description = "완등 영상 게시글 삭제 성공")
    @ApiResponse(responseCode = "404", description = "해당하는 영상 게시글을 찾을 수 없음")
    @ApiResponse(responseCode = "404", description = "작성자와 유저 정보가 일치하지 않음")
    @PreAuthorizeMember
    @DeleteMapping("board/detail")
    public BaseResponse<Object> deleteSuccessPost(@AuthenticationPrincipal MemberDetails memberDetails, @Valid @RequestParam Long videoBoardId) {
        Member member = memberDetails.getMember();
        videoBoardService.deleteSuccessPost(member.getId(), videoBoardId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @Operation(description = "내 완등 영상 게시글 목록 조회하기 API", summary = "내 완등 영상 게시글 목록 조회하기 API")
    @ApiResponse(responseCode = "200", description = "내 완등 영상 글 목록 불러오기 성공")
    @PreAuthorizeMember
    @GetMapping("board/mypost")
    public BaseResponse<List<VideoBoardSummaryResponse>> getMySuccessVideoPosts
            (@AuthenticationPrincipal MemberDetails memberDetails, @RequestParam(required = false) Long storeId) {
        Member member = memberDetails.getMember();
        List<VideoBoardSummaryResponse> mySuccessPosts = videoBoardService.getMySuccessVideoPosts(member.getId(), storeId);
        return new BaseResponse<>(mySuccessPosts);
    }


}

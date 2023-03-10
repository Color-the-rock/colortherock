package org.anotherclass.colortherock.domain.video.service;

import lombok.AllArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.member.exception.MemberNotFoundException;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.exception.WrongMemberException;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoListResponse;
import org.anotherclass.colortherock.domain.video.dto.DeletedVideoDto;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.exception.NotVideoExtensionException;
import org.anotherclass.colortherock.domain.video.exception.VideoFileNameHasNotExtensionException;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.repository.VideoReadRepository;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.request.MySuccessVideoRequest;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.anotherclass.colortherock.domain.videoboard.request.LocalSuccessVideoUploadRequest;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VideoService {
    private final S3Service s3Service;
    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;
    private final VideoReadRepository videoReadRepository;

    private static final Integer PAGE_SIZE = 15;

    /**
     * ????????? ?????? ?????? ?????? ?????????
     *
     * @param memberDetails ????????? ?????? ??????
     * @param newVideo      ????????? ????????? ?????????
     * @param request       {@link LocalSuccessVideoUploadRequest}
     * @return ????????? ????????? id
     */
    @Transactional
    public Long uploadSuccessVideo(MemberDetails memberDetails,
                                   MultipartFile newVideo,
                                   LocalSuccessVideoUploadRequest request) {
        Member member = memberRepository.findById(memberDetails.getMember().getId())
                .orElseThrow(() -> new MemberNotFoundException(GlobalErrorCode.USER_NOT_FOUND));
        // S3 ?????? ?????? ??? URL ????????????
        String videoName = extractValidVideoName(member, newVideo);
        String s3URL = s3Service.upload(newVideo, videoName);
        // ????????? ????????? ???????????? S3 ?????? ??? URL ????????????
        String thumbnailName = extractValidThumbName(member);
        String thumbnailURL = s3Service.uploadThumbnail(newVideo, thumbnailName);
        // request??? URL, name ??? DB??? ??????
        return saveSuccessVideo(member, videoName, s3URL, thumbnailName, thumbnailURL, request);
    }

    /**
     * ?????? ?????? ?????? ????????? ?????????
     * @param memberDetails ????????? ????????? ??????
     * @param newVideo ???????????? ??? ??????
     * @param request {@link UploadVideoRequest}
     */
    @Transactional
    public void uploadMyVideo(MemberDetails memberDetails, MultipartFile newVideo, UploadVideoRequest request) {
        Member member = memberRepository.findById(memberDetails.getMember().getId())
                .orElseThrow(() -> new MemberNotFoundException(GlobalErrorCode.USER_NOT_FOUND));
        // S3 ?????? ?????? ??? URL ????????????
        String videoName = extractValidVideoName(member, newVideo);
        String s3URL = s3Service.upload(newVideo, videoName);
        // ????????? ????????? ???????????? S3 ?????? ??? URL ????????????
        String thumbnailName = extractValidThumbName(member);
        String thumbnailURL = s3Service.uploadThumbnail(newVideo, thumbnailName);
        videoRepository.save(request.toEntity(member, s3URL, thumbnailURL, videoName, thumbnailName, false));
    }


    // ?????? ?????? ??????????????? ????????? ?????????
    @Transactional
    public Long saveSuccessVideo(Member member, String videoName, String s3URL, String thumbnailName, String thumbnailURL, LocalSuccessVideoUploadRequest request) {
        Video newVideo = Video.builder()
                .shootingDate(request.getShootingTime())
                .level(request.getLevel())
                .gymName(request.getGymName())
                .isSuccess(true)
                .color(request.getColor())
                .member(member)
                .s3URL(s3URL)
                .videoName(videoName)
                .thumbnailURL(thumbnailURL)
                .thumbnailName(thumbnailName)
                .isPosted(true)
                .build();
        Video saveVideo = videoRepository.save(newVideo);
        return saveVideo.getId();
    }

    /**
     * ??? ?????? ?????? ????????????
     * @param member ?????????
     * @param request {@link MySuccessVideoRequest}
     * @return {@link VideoListResponse} ????????? ????????? ??????
     */
    @Transactional(readOnly = true)
    public List<VideoListResponse> getMySuccessVideoList(Member member, MySuccessVideoRequest request) {
        Pageable pageable = Pageable.ofSize(PAGE_SIZE);
        Slice<Video> slices = videoReadRepository.searchBySuccessRequest(pageable, request, member);
        if (slices.isEmpty()) return new ArrayList<>();

        return slices.toList().stream()
                .map(video ->
                        VideoListResponse.builder()
                                .thumbnailURL(video.getThumbnailURL())
                                .id(video.getId())
                                .color(video.getColor())
                                .gymName(video.getGymName())
                                .level(video.getLevel()).build())
                .collect(Collectors.toList());

    }

    /**
     * ??? ?????? ????????????
     * @param member ?????????
     * @param videoId ?????? id
     * @return {@link DeletedVideoDto}
     */
    @Transactional
    public DeletedVideoDto deleteVideo(Member member, Long videoId) {
        // ?????? ???????????? member??? ????????? ????????? ???????????? ??? ??????
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new VideoNotFoundException(GlobalErrorCode.VIDEO_NOT_FOUND));
        String videoName = video.getVideoName();
        Boolean isVideoSuccess = video.getIsSuccess();
        if (member.getId().longValue() != video.getMember().getId().longValue())
            throw new WrongMemberException(GlobalErrorCode.NOT_VIDEO_OWNER);
        videoRepository.deleteById(videoId);
        return new DeletedVideoDto(videoName, isVideoSuccess);
    }

    /**
     * ?????? ?????? ????????? validation
     * @param member ?????????
     * @param newVideo ????????? ?????????
     * @return ??????????????? ?????? ????????? ????????? ?????????.
     */
    public String extractValidVideoName(Member member, MultipartFile newVideo) {
        String fileName = newVideo.getOriginalFilename();
        assert fileName != null;
        if (fileName.split("\\.").length < 2) {
            throw new VideoFileNameHasNotExtensionException(GlobalErrorCode.VIDEO_HAS_NOT_EXTENSION);
        }
        String[] split = fileName.split("\\.");
        String extension = split[split.length - 1].toLowerCase();
        if (!extension.matches("(mp4|mov|avi|wmv|flv|mkv|webm)$")) {
            throw new NotVideoExtensionException(GlobalErrorCode.NOT_VIDEO_EXTENSION);
        }
        return System.currentTimeMillis() + member.getNickname() + "." + extension;
    }

    /**
     * ???????????? ?????? ?????? ?????????
     * @param member ??????
     * @return ?????? ?????? ??????
     */
    public String extractValidThumbName(Member member) {
        return "Thumb" + System.currentTimeMillis() + member.getNickname() + ".JPEG";
    }

}

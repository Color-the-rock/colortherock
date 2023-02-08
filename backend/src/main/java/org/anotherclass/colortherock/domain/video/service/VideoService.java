package org.anotherclass.colortherock.domain.video.service;

import lombok.AllArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.exception.NotVideoExtensionException;
import org.anotherclass.colortherock.domain.video.exception.VideoFileNameHasNotExtensionException;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.anotherclass.colortherock.domain.videoboard.request.LocalSuccessVideoUploadRequest;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    // 마이페이지에서 동영상 업로드
    @Transactional
    public void uploadVideo(Member member, String s3URL, String thumbnailURL, UploadVideoRequest request, String videoName) {
        videoRepository.save(request.toEntity(member, s3URL, thumbnailURL, videoName));
    }

    // 완등 영상 게시판에서 동영상 업로드
    @Transactional
    public Long uploadSuccessVideo(Member member, String s3URL, String thumbnailURL, LocalSuccessVideoUploadRequest request) {
        Video newVideo = Video.builder()
                    .shootingDate(request.getShootingTime())
                    .level(request.getLevel())
                    .gymName(request.getGymName())
                    .isSuccess(true)
                    .color(request.getColor())
                    .member(member)
                    .s3URL(s3URL)
                    .thumbnailURL(thumbnailURL)
                    .build();
        Video saveVideo = videoRepository.save(newVideo);
        return saveVideo.getId();
    }

    @Transactional
    public void deleteVideo(Long videoId) {
        videoRepository.deleteById(videoId);
    }

    public String extractValidVideoName(Member member, MultipartFile newVideo) {
        String fileName = newVideo.getOriginalFilename();
        if (fileName.split("\\.").length < 2) {
            throw new VideoFileNameHasNotExtensionException(GlobalErrorCode.VIDEO_HAS_NOT_EXTENSION);
        }
        String[] split = fileName.split("\\.");
        String extension = split[split.length - 1];
        if (!extension.matches("(mp4|mov|avi|wmv|flv|mkv|webm)$")) {
            throw new NotVideoExtensionException(GlobalErrorCode.NOT_VIDEO_EXTENSION);
        }
        String videoName = DateTime.now() + member.getNickname() + "." + extension;
        return videoName;
    }

    public String extractValidThumbName(Member member) {
        return "Thumb" + DateTime.now() + member.getNickname() + ".JPEG";
    }

}

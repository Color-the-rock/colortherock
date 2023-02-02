package org.anotherclass.colortherock.domain.video.service;

import lombok.AllArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.request.UploadVideoRequest;
import org.anotherclass.colortherock.domain.videoboard.request.LocalSuccessVideoUploadRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

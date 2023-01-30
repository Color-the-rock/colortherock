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
    public void uploadVideo(Member member, String s3URL, UploadVideoRequest request) {
        videoRepository.save(request.toEntity(member, s3URL));
    }

    // 완등 영상 게시판에서 동영상 업로드
    @Transactional
    public Long uploadSuccessVideo(Member member, String s3URL, LocalSuccessVideoUploadRequest request) {
        Video newVideo = videoRepository.save(request.toEntity(member, s3URL, request));
        return newVideo.getId();
    }

    @Transactional
    public void deleteVideo(Long videoId) {
        videoRepository.deleteById(videoId);
    }
}

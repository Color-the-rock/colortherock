package org.anotherclass.colortherock.domain.memberrecord.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.TotalStatResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoDetailResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoListResponse;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.LevelStatResponse;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final VideoRepository videoRepository;
    private final RecordRepository recordRepository;

    @Transactional(readOnly = true)
    public List<LevelStatResponse> getColorRecords(Member member) {
        List<LevelStatResponse> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new LevelStatResponse(i + 1));
        }
        List<Video> videos = videoRepository.findAllByMember(member);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            LevelStatResponse dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }

    @Transactional(readOnly = true)
    public List<LevelStatResponse> getDateRecords(Member member, LocalDate videoDate) {
        List<LevelStatResponse> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new LevelStatResponse(i + 1));
        }
        List<Video> videos = videoRepository.findAllByMemberAndShootingDate(member, videoDate);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            LevelStatResponse dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }

    @Transactional(readOnly = true)
    public TotalStatResponse getTotalRecords(Member member) {
        MemberRecord memberRecord = recordRepository.findByMember(member);
        return new TotalStatResponse(memberRecord.getVideoCount(), memberRecord.getVideoLengthSum(), memberRecord.getSuccessCount());
    }

    @Transactional(readOnly = true)
    public List<VideoListResponse> getSuccessVideos(Member member, LocalDate videoDate) {
        List<Video> successVideos = videoRepository.findAllByMemberAndShootingDateAndIsSuccessIsTrue(member, videoDate);
        List<VideoListResponse> successResponses = new ArrayList<>();
        for (Video video : successVideos) {
            successResponses.add(VideoListResponse.builder()
                            .id(video.getId())
                            .color(video.getColor())
                            .gymName(video.getGymName())
                            .level(video.getLevel())
                            .thumbnailURL(video.getThumbnailURL()).build());
        }
        return successResponses;
    }

    public List<VideoListResponse> getFailVideos(Member member, LocalDate videoDate) {
        List<Video> failVideos = videoRepository.findAllByMemberAndShootingDateAndIsSuccessIsFalse(member, videoDate);
        List<VideoListResponse> failResponses = new ArrayList<>();
        for (Video video : failVideos) {
            failResponses.add(VideoListResponse.builder()
                    .id(video.getId())
                    .color(video.getColor())
                    .gymName(video.getGymName())
                    .level(video.getLevel())
                    .thumbnailURL(video.getThumbnailURL()).build());
        }
        return failResponses;
    }

    @Transactional(readOnly = true)
    public VideoDetailResponse getVideoDetail(Long id) {
        Optional<Video> optional = videoRepository.findById(id);
        if(optional.isEmpty()) {
            throw new VideoNotFoundException(GlobalErrorCode.VIDEO_NOT_FOUND);
        } else {
            Video video = optional.get();
            return VideoDetailResponse.builder()
                    .s3URL(video.getS3URL())
                    .isSuccess(video.getIsSuccess())
                    .shootingDate(video.getShootingDate().toString())
                    .level(video.getLevel())
                    .color(video.getColor())
                    .gymName(video.getGymName())
                    .id(video.getId()).build();
        }
    }
}

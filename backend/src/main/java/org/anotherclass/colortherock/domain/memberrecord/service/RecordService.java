package org.anotherclass.colortherock.domain.memberrecord.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.TotalStatResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoDetailResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoListResponse;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.repository.VideoReadRepository;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.LevelStatResponse;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.request.MyVideoRequest;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final VideoRepository videoRepository;
    private final RecordRepository recordRepository;
    private final VideoReadRepository videoReadRepository;

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


    // 향후 데이터 읽는 것으로 변환해야함@@@@
    @Transactional(readOnly = true)
    public TotalStatResponse getTotalRecords(Member member) {
        MemberRecord memberRecord = recordRepository.findByMember(member);
        return new TotalStatResponse(memberRecord.getVideoCount(), memberRecord.getVideoLengthSum(), memberRecord.getSuccessCount());
    }

    @Transactional(readOnly = true)
    public List<VideoListResponse> getMyVideos(Pageable pageable, MyVideoRequest request) {
        Slice<Video> slices = videoReadRepository.searchBySlice(pageable, request);

        if(slices.isEmpty()) return new ArrayList<>();

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

    @Transactional(readOnly = true)
    public VideoDetailResponse getVideoDetail(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(GlobalErrorCode.VIDEO_NOT_FOUND));

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

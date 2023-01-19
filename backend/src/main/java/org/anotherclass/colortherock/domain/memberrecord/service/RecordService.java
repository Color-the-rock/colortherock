package org.anotherclass.colortherock.domain.memberrecord.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.TotalStatResponse;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoListResponse;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.LevelStatResponse;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final VideoRepository videoRepository;
    private final RecordRepository recordRepository;

    @Transactional
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

    @Transactional
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


    public TotalStatResponse getTotalRecords(Member member) {
        MemberRecord memberRecord = recordRepository.findByMember(member);
        return new TotalStatResponse(memberRecord.getVideoCount(), memberRecord.getVideoLengthSum(), memberRecord.getSuccessCount());
    }

    public List<VideoListResponse> getSuccessVideos(Member member, LocalDate videoDate) {
        List<Video> successVideos = videoRepository.findAllByMemberAndShootingDateAndIsSuccessIsTrue(member, videoDate);
        List<VideoListResponse> successDTOs = new ArrayList<>();
        for (Video video : successVideos) {
            successDTOs.add(video.toVideoListDTO());
        }
        return successDTOs;
    }
}

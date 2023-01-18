package org.anotherclass.colortherock.domain.memberrecord.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.TotalStatDTO;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.LevelStatDTO;
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
    public List<LevelStatDTO> getColorRecords(Member member) {
        List<LevelStatDTO> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new LevelStatDTO(i + 1));
        }
        List<Video> videos = videoRepository.findAllByMember(member);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            LevelStatDTO dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }

    @Transactional
    public List<LevelStatDTO> getDateRecords(Member member, LocalDate videoDate) {
        List<LevelStatDTO> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new LevelStatDTO(i + 1));
        }
        List<Video> videos = videoRepository.findAllByMemberAndShootingDate(member, videoDate);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            LevelStatDTO dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }

    public TotalStatDTO getTotalRecords(Member member) {
        MemberRecord memberRecord = recordRepository.findByMember(member);
        return memberRecord.toTotalDTO();
    }
}

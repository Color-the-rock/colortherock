package org.anotherclass.colortherock.domain.memberrecord.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.StatisticsDTO;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Transactional
    public List<StatisticsDTO> getColorRecords(Member member) {
        List<StatisticsDTO> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new StatisticsDTO(i + 1));
        }
        List<Video> videos = recordRepository.findAllByMember(member);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            StatisticsDTO dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }

    @Transactional
    public List<StatisticsDTO> getDateRecords(Member member, LocalDate videoDate) {
        List<StatisticsDTO> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new StatisticsDTO(i + 1));
        }
        List<Video> videos = recordRepository.findAllByMemberAndShootingDate(member, videoDate);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            StatisticsDTO dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }
}

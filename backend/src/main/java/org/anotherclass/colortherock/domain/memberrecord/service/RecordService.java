package org.anotherclass.colortherock.domain.memberrecord.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.StatisticsDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Transactional
    public List<StatisticsDTO> getColorRecords(Member member) {
        List<StatisticsDTO> list = new ArrayList<>();
        for (int level = 1; level < 9; level++) {
            Long success = recordRepository.countByMemberAndLevelAndIsSuccessIsTrue(member, level);
            Long total = recordRepository.countByMemberAndLevel(member, level);
            StatisticsDTO dto = new StatisticsDTO(level, total, success);
            list.add(dto);
        }
        return list;
    }
}

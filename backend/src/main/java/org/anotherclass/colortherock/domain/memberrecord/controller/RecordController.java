package org.anotherclass.colortherock.domain.memberrecord.controller;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.memberrecord.response.StatisticsDTO;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    /**
     * 전체 운동 영상 색상 별 통계 조회
     */
    @GetMapping("/color")
    public ResponseEntity<List<StatisticsDTO>> recordsByColor(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        List<StatisticsDTO> colorRecords = recordService.getColorRecords(member);
        return ResponseEntity.ok().body(colorRecords);
    }

}

package org.anotherclass.colortherock.domain.memberrecord.controller;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.entity.MemberDetails;
import org.anotherclass.colortherock.domain.memberrecord.exception.MalformedDateException;
import org.anotherclass.colortherock.domain.memberrecord.response.LevelStatDTO;
import org.anotherclass.colortherock.domain.memberrecord.response.TotalStatDTO;
import org.anotherclass.colortherock.domain.memberrecord.response.VideoListDTO;
import org.anotherclass.colortherock.domain.memberrecord.service.RecordService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
    public BaseResponse<List<LevelStatDTO>> recordsByColor(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        List<LevelStatDTO> colorRecords = recordService.getColorRecords(member);
        return new BaseResponse<>(colorRecords);
    }

    /**
     * 날짜별 운동 기록 색상 별 조회
     */
    @GetMapping("/color/{date}")
    public BaseResponse<List<LevelStatDTO>> recordsByColorAndDate(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String date) throws MalformedDateException {
        Member member = memberDetails.getMember();
        // 날짜 형식이 YYYY-MM-DD 이 아닌 경우 예외 발생
        if(!date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
            throw new MalformedDateException(GlobalErrorCode.MALFORMED_DATE);
        }
        LocalDate videoDate = LocalDate.parse(date);
        List<LevelStatDTO> dateRecords = recordService.getDateRecords(member, videoDate);
        return new BaseResponse<>(dateRecords);
    }

    /**
     * 전체 운동 기록 누적 통계 조회
     */
    @GetMapping("/total")
    public BaseResponse<TotalStatDTO> recordsTotal(@AuthenticationPrincipal MemberDetails memberDetails) {
        Member member = memberDetails.getMember();
        TotalStatDTO totalStatDTO = recordService.getTotalRecords(member);
        return new BaseResponse<>(totalStatDTO);
    }

    /**
     * 날짜별 운동 영상 목록 조회(성공 영상)
     */
    @GetMapping("/videos/success/{date}")
    public BaseResponse<List<VideoListDTO>> successVideosByDate(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable String date) {
        Member member = memberDetails.getMember();
        // 날짜 형식이 YYYY-MM-DD 이 아닌 경우 예외 발생
        if(!date.matches("\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")) {
            throw new MalformedDateException(GlobalErrorCode.MALFORMED_DATE);
        }
        LocalDate videoDate = LocalDate.parse(date);
        List<VideoListDTO> successDTOs = recordService.getSuccessVideos(member, videoDate);
        return new BaseResponse<>(successDTOs);
    }
}
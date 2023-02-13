package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "운동 기록 캘린더 색상 반환")
public class DailyColorResponse {

    @Schema(description = "날짜")
    private LocalDate date;
    @Schema(description = "색깔")
    private List<String> colors;

    @Builder
    public DailyColorResponse(LocalDate date, List<String> colors) {
        this.date = date;
        this.colors = colors;
    }
}

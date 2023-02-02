package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class DailyColorResponse {
    private LocalDate date;
    private List<String> colors;

    @Builder
    public DailyColorResponse(LocalDate date, List<String> colors) {
        this.date = date;
        this.colors = colors;
    }
}

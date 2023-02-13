package org.anotherclass.colortherock.domain.video.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class DateLevelDto {
    private LocalDate date;
    private Integer level;

    @Builder
    public DateLevelDto(LocalDate date, Integer level) {
        this.date = date;
        this.level = level;
    }
}

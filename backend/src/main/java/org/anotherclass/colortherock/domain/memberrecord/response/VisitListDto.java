package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VisitListDto {
    private String gymName;
    private Long count;

    @Builder
    public VisitListDto(String gymName, Long count) {
        this.gymName = gymName;
        this.count = count;
    }
}

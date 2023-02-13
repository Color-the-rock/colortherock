package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "방문 통계 응답")
public class VisitListDto {
    @Schema(description = "암장 이름")
    private String gymName;
    @Schema(description = "방문 횟수")
    private Long count;

    @Builder
    public VisitListDto(String gymName, Long count) {
        this.gymName = gymName;
        this.count = count;
    }
}

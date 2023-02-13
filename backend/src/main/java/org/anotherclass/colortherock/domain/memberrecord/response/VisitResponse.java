package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "방문 통계 응답")
public class VisitResponse {
    @Schema(description = "총 방문 횟수")
    private Long totalCount;
    @Schema(description = "방문 통계 데이터 ")
    private List<VisitListDto> data;
    @Builder
    public VisitResponse(Long totalCount, List<VisitListDto> data) {
        this.totalCount = totalCount;
        this.data = data;
    }
}

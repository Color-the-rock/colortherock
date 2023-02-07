package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "전체 운동 기록 통계 응답 객체")
public class TotalStatResponse {

    @Schema(description = "영상 개수")
    Integer videoCount;
    @Schema(description = "성공 개수")
    Integer successCount;

    @Schema(description = "방문 횟수")
    Integer visitCount;
    @Builder
    public TotalStatResponse(Integer videoCount, Integer successCount, Integer visitCount) {
        this.videoCount = videoCount;
        this.successCount = successCount;
        this.visitCount = visitCount;
    }
}

package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TotalStatResponse {
    Integer videoCount;
    Integer successCount;
    Integer visitCount;
    @Builder
    public TotalStatResponse(Integer videoCount, Integer successCount, Integer visitCount) {
        this.videoCount = videoCount;
        this.successCount = successCount;
        this.visitCount = visitCount;
    }
}

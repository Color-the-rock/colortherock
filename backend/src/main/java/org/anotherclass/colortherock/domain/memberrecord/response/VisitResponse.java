package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class VisitResponse {
    private Long totalCount;
    private List<VisitListDto> data;
    @Builder
    public VisitResponse(Long totalCount, List<VisitListDto> data) {
        this.totalCount = totalCount;
        this.data = data;
    }
}

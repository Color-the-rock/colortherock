package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;

import java.util.List;

public class VisitResponse {
    private Long totalCount;
    private List<VisitListDto> data;
    @Builder
    public VisitResponse(Long totalCount, List<VisitListDto> data) {
        this.totalCount = totalCount;
        this.data = data;
    }
}

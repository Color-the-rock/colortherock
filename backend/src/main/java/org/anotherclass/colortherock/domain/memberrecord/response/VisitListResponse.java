package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VisitListResponse {
    private String gymName;
    private Long count;

    @Builder
    public VisitListResponse(String gymName, Long count) {
        this.gymName = gymName;
        this.count = count;
    }
}

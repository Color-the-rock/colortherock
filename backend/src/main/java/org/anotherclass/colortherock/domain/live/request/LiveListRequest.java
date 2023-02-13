package org.anotherclass.colortherock.domain.live.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LiveListRequest {
    private Long liveId;
    private String gymName;
    @Builder
    public LiveListRequest(Long liveId, String gymName) {
        this.liveId = liveId;
        this.gymName = gymName;
    }
}

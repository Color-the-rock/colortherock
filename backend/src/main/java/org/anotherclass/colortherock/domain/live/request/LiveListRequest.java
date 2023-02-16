package org.anotherclass.colortherock.domain.live.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LiveListRequest {
    private Long liveId;

    @Override
    public String toString() {
        return "LiveListRequest{" +
                "liveId=" + liveId +
                ", gymName='" + gymName + '\'' +
                '}';
    }

    private String gymName;

    @Builder
    public LiveListRequest(Long liveId, String gymName) {
        this.liveId = liveId;
        this.gymName = gymName;
    }
}

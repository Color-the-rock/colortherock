package org.anotherclass.colortherock.domain.live.response;

import lombok.Builder;

public class LiveListResponse {
    private Long id;
    private String title;
    private String memberName;
    private Long memberId;
    private String gymName;
    private String sessionId;
    private Integer participantNum;

    @Builder
    public LiveListResponse(Long id, String title, String memberName, Long memberId, String gymName, String sessionId, Integer participantNum) {
        this.id = id;
        this.title = title;
        this.memberName = memberName;
        this.memberId = memberId;
        this.gymName = gymName;
        this.sessionId = sessionId;
        this.participantNum = participantNum;
    }
}

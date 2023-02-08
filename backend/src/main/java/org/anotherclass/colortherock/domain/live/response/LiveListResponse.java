package org.anotherclass.colortherock.domain.live.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LiveListResponse {

    @Schema(description = "라이브 id")
    private Long id;
    @Schema(description = "라이브 방 제목")
    private String title;
    @Schema(description = "방송하는사람 이름")
    private String memberName;
    @Schema(description = "방송하는 사람 id")
    private Long memberId;
    @Schema(description = "암장 이름")
    private String gymName;
    @Schema(description = "세션 id")
    private String sessionId;
    @Schema(description = "참가자 수")
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

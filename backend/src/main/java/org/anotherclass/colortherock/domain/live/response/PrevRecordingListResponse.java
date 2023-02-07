package org.anotherclass.colortherock.domain.live.response;

import io.openvidu.java.client.Recording;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class PrevRecordingListResponse {

    @Schema(description = "녹화 영상 id")
    private String recordingId;
    @Schema(description = "녹화 영상 제목")
    private String recordingName;

    @Schema(description = "세션 id")
    private String sessionId;

    @Schema(description = "녹화 경로 url")
    private String url;
    @Schema(description = "생성 시간")
    private Long createdAt;

    @Schema(description = "녹화 시간")
    private Double duration;

    @Builder
    public PrevRecordingListResponse(String recordingId, String recordingName, String sessionId, String url) {
        this.recordingId = recordingId;
        this.recordingName = recordingName;
        this.sessionId = sessionId;
        this.url = url;
    }

    public PrevRecordingListResponse(Recording recording) {
        this.recordingId = recording.getId();
        this.recordingName = recording.getName();
        this.sessionId = recording.getSessionId();
        this.url = recording.getUrl();
        this.createdAt = recording.getCreatedAt();
        this.duration = recording.getDuration();
    }
}

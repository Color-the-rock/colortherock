package org.anotherclass.colortherock.domain.live.response;

import io.openvidu.java.client.Recording;
import lombok.Builder;

public class RecordingListResponse {
    private String recordingId;
    private String recordingName;
    private String sessionId;
    private String  url;

    @Builder
    public RecordingListResponse(String recordingId, String recordingName, String sessionId, String url) {
        this.recordingId = recordingId;
        this.recordingName = recordingName;
        this.sessionId = sessionId;
        this.url = url;
    }

    public RecordingListResponse(Recording recording) {
        this.recordingId = recording.getId();
        this.recordingName = recording.getName();
        this.sessionId = recording.getSessionId();
        this.url = recording.getUrl();
    }
}

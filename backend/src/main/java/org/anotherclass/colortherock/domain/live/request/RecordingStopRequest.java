package org.anotherclass.colortherock.domain.live.request;

import lombok.Data;

@Data
public class RecordingStopRequest {
    private String token;
    private String recordingId;
}

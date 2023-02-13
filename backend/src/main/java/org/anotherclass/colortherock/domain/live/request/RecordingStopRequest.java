package org.anotherclass.colortherock.domain.live.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "녹화 중지 요청")
public class RecordingStopRequest {
    @Schema(description = "토큰")
    @NotBlank
    private String token;
    @Schema(description = "녹화 id")
    @NotBlank
    private String recordingId;
}

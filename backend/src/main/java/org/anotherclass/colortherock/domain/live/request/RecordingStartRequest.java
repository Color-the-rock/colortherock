package org.anotherclass.colortherock.domain.live.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "녹화 시작 요청")
public class RecordingStartRequest {
    @Schema(description = "커넥션 id")
    @NotBlank
    private String connectionId;
}

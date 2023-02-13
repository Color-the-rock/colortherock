package org.anotherclass.colortherock.domain.videoboard.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "성공 영상 업로드 요청")
public class SuccessVideoUploadRequest {

    @NotNull
    @Schema(description = "영상 id")
    Long videoId;

    @NotNull
    @Schema(description = "제목")
    String title;
}

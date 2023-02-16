package org.anotherclass.colortherock.domain.report.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "영상 숨김 해제 요청")
public class PostUnhiddenRequest {
    @NotNull
    @Schema(description = "숨김해제 할 영상 ID")
    private Long videoBoardId;
}

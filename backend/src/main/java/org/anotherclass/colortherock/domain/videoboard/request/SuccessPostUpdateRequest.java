package org.anotherclass.colortherock.domain.videoboard.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "영상 업데이트 요청")
public class SuccessPostUpdateRequest {

    @NotNull
    @Schema(description = "영상 게시글 id")
    private Long videoBoardId;
    @NotNull
    @Schema(description = "게시글 제목")
    private String title;
}

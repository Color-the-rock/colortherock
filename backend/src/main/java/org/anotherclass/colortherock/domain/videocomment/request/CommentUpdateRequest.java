package org.anotherclass.colortherock.domain.videocomment.request;

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
@Schema(description = "댓글 수정 요청")
public class CommentUpdateRequest {

    @NotNull
    @Schema(description = "댓글 id")
    private Long commentId;
    @NotNull
    @Schema(description = "댓글 내용")
    private String content;

}

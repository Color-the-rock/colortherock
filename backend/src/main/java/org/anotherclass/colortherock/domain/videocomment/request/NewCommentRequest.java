package org.anotherclass.colortherock.domain.videocomment.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 생성 요청")
public class NewCommentRequest {

    @NotNull
    @Schema(description = "게시글 id")
    private Long videoBoardId;
    @NotNull
    @Schema(description = "댓글 내용")
    private String content;


}

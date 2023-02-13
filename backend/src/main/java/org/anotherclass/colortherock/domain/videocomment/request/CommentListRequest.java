package org.anotherclass.colortherock.domain.videocomment.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 리스트 요청")
public class CommentListRequest {

    @Schema(description = "마지막으로 본 댓글 id")
    @NotNull
    private Long storeId;
    @NotNull
    @Schema(description = "영상 게시글 id")
    private Long videoBoardId;


}

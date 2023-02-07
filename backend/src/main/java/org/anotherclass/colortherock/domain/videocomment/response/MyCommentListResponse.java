package org.anotherclass.colortherock.domain.videocomment.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "내 댓글 리스트 응답")
public class MyCommentListResponse {
    @Schema(description = "댓글 id")
    private Long commentId;
    @Schema(description = "게시글 id")
    private Long videoBoardId;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "생성 시간")
    private LocalDateTime createdDate;

}

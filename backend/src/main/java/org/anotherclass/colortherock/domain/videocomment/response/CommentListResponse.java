package org.anotherclass.colortherock.domain.videocomment.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 리스트 응답")
public class CommentListResponse {

    @Schema(description = "댓글 id")
    private Long commentId;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "생성 날짜")
    private LocalDate createdDate;
}

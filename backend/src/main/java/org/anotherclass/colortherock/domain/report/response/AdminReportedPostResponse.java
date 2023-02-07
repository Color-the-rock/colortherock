package org.anotherclass.colortherock.domain.report.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "신고처리된 게시글 보기 응답")
public class AdminReportedPostResponse {
    @Schema(description = "영상 게시글 ide")
    private Long videoBoardId;
    @Schema(description = "게시글 제목")
    private String title;


}

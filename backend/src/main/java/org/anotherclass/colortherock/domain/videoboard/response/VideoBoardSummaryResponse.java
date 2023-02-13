package org.anotherclass.colortherock.domain.videoboard.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 요약 응답")
public class VideoBoardSummaryResponse {

    @Schema(description = "게시글 id")
    private Long videoBoardId;
    @Schema(description = "제목")
    private String title;

    @Schema(description = "섬네일 url")
    private String thumbnailURL;
    @Schema(description = "색깔")
    private String color;
    @Schema(description = "암장 이름")
    private String gymName;
    @Schema(description = "생성 시간")
    private LocalDate createdDate;
}

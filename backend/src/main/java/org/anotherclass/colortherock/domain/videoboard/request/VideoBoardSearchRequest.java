package org.anotherclass.colortherock.domain.videoboard.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "영상 게시글 검색 요청")
public class VideoBoardSearchRequest {

    @Schema(description = "마지막으로 본 게시글 id",nullable = true)
    private Long storeId;
    @Schema(description = "색깔",nullable = true)
    private String color;
    @Schema(description = "암장 이름",nullable = true)
    private String gymName;
}

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
@Schema(description = "영상 게시글 검색 요청")
public class VideoBoardSearchRequest {

    @NotNull
    @Schema(description = "마지막으로 본 게시글 id")
    private Long storeId;
    @Schema(description = "색깔",nullable = true)
    private String color;
    @Schema(description = "암장 이름",nullable = true)
    private String gymName;
}

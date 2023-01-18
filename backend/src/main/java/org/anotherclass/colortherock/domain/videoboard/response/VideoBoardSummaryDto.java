package org.anotherclass.colortherock.domain.videoboard.response;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class VideoBoardSummaryDto {
    @NotNull
    private Long videoBoardId;
    @NotBlank
    private String title;
    @NotBlank
    private String thumbnailURL;
    @NotBlank
    private String color;
    @NotNull
    private LocalDateTime writtenTime;
}

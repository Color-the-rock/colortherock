package org.anotherclass.colortherock.domain.videoboard.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class VideoBoardSummaryDto {
    private Long videoBoardId;
    private String title;
    private String thumbnailURL;
    private String color;
    private LocalDateTime writtenTime;
}

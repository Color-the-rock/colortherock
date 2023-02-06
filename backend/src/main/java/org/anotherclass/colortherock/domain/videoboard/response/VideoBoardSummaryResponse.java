package org.anotherclass.colortherock.domain.videoboard.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoBoardSummaryResponse {

    private Long videoBoardId;
    private String title;
    private String thumbnailURL;
    private String color;
    private String gymName;
    private LocalDate createdDate;
}

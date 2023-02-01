package org.anotherclass.colortherock.domain.videoboard.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoBoardDetailResponse {
    private Long videoBoardId;
    private String nickname;
    private String title;
    private LocalDateTime createdDate;

}

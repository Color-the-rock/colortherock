package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoBoardSearchRequest {
    private Long storeId;
    private String color;
    private String gymName;
}

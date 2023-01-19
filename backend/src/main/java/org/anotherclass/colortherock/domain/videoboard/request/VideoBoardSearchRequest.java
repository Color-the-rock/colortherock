package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class VideoBoardSearchRequest {
    private Long storeId;
    private String color;
    private String gymName;
}

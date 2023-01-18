package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
public class VideoBoardSearchDto {
    private Long storeId;
    private String color;
    private String gymName;
}

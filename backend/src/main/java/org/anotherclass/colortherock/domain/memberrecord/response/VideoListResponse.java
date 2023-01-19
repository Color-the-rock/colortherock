package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoListResponse {
    Long id;
    String thumbnailURL;
    String gymName;
    Integer level;
    String color;

    @Builder
    public VideoListResponse(Long id, String thumbnailURL, String gymName, Integer level, String color) {
        this.id = id;
        this.thumbnailURL = thumbnailURL;
        this.gymName = gymName;
        this.level = level;
        this.color = color;
    }
}

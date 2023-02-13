package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "해당 날짜 영상 리스트 조회 응답 객체")
public class VideoListResponse {
    @Schema(description = "영상 id")
    Long videoId;
    @Schema(description = "섬네일 url")
    String thumbnailURL;
    @Schema(description = "암장 이름")
    String gymName;
    @Schema(description = "레벨")
    Integer level;
    @Schema(description = "색깔")
    String color;

    @Builder
    public VideoListResponse(Long id, String thumbnailURL, String gymName, Integer level, String color) {
        this.videoId = id;
        this.thumbnailURL = thumbnailURL;
        this.gymName = gymName;
        this.level = level;
        this.color = color;
    }
}

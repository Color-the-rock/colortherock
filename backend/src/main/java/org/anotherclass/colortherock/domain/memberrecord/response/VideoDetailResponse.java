package org.anotherclass.colortherock.domain.memberrecord.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "영상 정보 조회 응답")
public class VideoDetailResponse {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "찍은 날짜")
    private String shootingDate;

    @Schema(description = "레벨")
    private Integer level;

    @Schema(description = "암장 이름")
    private String gymName;

    @Schema(description = "s3 저장 url")
    private String s3URL;
    @Schema(description = "성공 여부")
    private Boolean isSuccess;

    @Schema(description = "색깔")
    private String color;

    @Builder
    public VideoDetailResponse(Long id, String shootingDate, Integer level, String gymName, String s3URL, boolean isSuccess, String color) {
        this.id = id;
        this.shootingDate = shootingDate;
        this.level = level;
        this.gymName = gymName;
        this.s3URL = s3URL;
        this.isSuccess = isSuccess;
        this.color = color;
    }
}

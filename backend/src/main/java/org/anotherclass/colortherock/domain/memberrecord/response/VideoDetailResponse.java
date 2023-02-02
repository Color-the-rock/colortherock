package org.anotherclass.colortherock.domain.memberrecord.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoDetailResponse {
    private Long id;
    private String shootingDate;
    private Integer level;
    private String gymName;
    private String s3URL;
    private Boolean isSuccess;
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

package org.anotherclass.colortherock.domain.video.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Schema(description = "영상 업로드 요청")
public class UploadVideoRequest {
    @NotNull
    @Schema(description = "찍은 날짜")
    private LocalDate shootingDate;

    @NotNull
    @Schema(description = "레벨")
    private Integer level;
    @NotNull
    @Schema(description = "색깔")
    private String color;
    @NotNull
    @Schema(description = "암장 이름")
    private String gymName;
    @NotNull
    @Schema(description = "성공 여부")
    private Boolean isSuccess;
    @Builder
    public UploadVideoRequest(LocalDate shootingDate, Integer level, String color, String gymName, Boolean isSuccess) {
        this.shootingDate = shootingDate;
        this.level = level;
        this.color = color;
        this.gymName = gymName;
        this.isSuccess = isSuccess;
    }

    public Video toEntity(Member member) {
        return Video.builder()
                .shootingDate(this.shootingDate)
                .level(this.level)
                .gymName(this.gymName)
                .isSuccess(this.isSuccess)
                .color(this.color)
                .member(member).build();
    }

    public Video toEntity(Member member, String s3URL, String thumbnailURL, String videoName, String thumbnailName, Boolean isPosted) {
        return Video.builder()
                .shootingDate(this.shootingDate)
                .level(this.level)
                .gymName(this.gymName)
                .isSuccess(this.isSuccess)
                .color(this.color)
                .isPosted(isPosted)
                .member(member)
                .s3URL(s3URL)
                .thumbnailURL(thumbnailURL)
                .videoName(videoName)
                .thumbnailName(thumbnailName)
                .build();
    }

}


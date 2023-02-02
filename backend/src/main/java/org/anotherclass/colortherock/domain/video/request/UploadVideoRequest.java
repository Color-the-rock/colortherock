package org.anotherclass.colortherock.domain.video.request;

import lombok.*;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class UploadVideoRequest {
    @NotNull
    private LocalDate shootingDate;
    @NotNull
    private Integer level;
    @NotNull
    private String color;
    @NotNull
    private String gymName;
    @NotNull
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

    public Video toEntity(Member member, String s3URL, String thumbnailURL, String videoName) {
        return Video.builder()
                .shootingDate(this.shootingDate)
                .level(this.level)
                .gymName(this.gymName)
                .isSuccess(this.isSuccess)
                .color(this.color)
                .member(member)
                .s3URL(s3URL)
                .thumbnailURL(thumbnailURL)
                .videoName(videoName)
                .build();
    }

}


package org.anotherclass.colortherock.domain.video.request;

import lombok.*;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UploadVideoRequest {
    @NotNull
    private LocalDate shootingDate;
    @NotNull
    private Integer level;
    @NotNull
    private String gymName;
    @NotNull
    private Boolean isSuccess;
    @NotNull
    private String color;
    private Member member;
    private String s3URL;
    private String videoName;

    public UploadVideoRequest(LocalDate shootingDate, Integer level, String gymName, Boolean isSuccess, String color, Member member) {
        this.shootingDate = shootingDate;
        this.level = level;
        this.gymName = gymName;
        this.isSuccess = isSuccess;
        this.color = color;
        this.member = member;
    }

    public UploadVideoRequest(LocalDate shootingDate, Integer level, String gymName, Boolean isSuccess, String color, Member member, String videoName) {
        this.shootingDate = shootingDate;
        this.level = level;
        this.gymName = gymName;
        this.isSuccess = isSuccess;
        this.color = color;
        this.member = member;
        this.videoName = videoName;
    }

    public Video toEntity() {
        return Video.builder()
            .shootingDate(this.shootingDate)
            .level(this.level)
            .gymName(this.gymName)
            .isSuccess(this.isSuccess)
            .color(this.color)
            .member(this.member)
            .videoName(this.videoName).build();
    }

    public Video toEntity(Member member, String s3URL) {
        return Video.builder()
        .shootingDate(this.shootingDate)
        .level(this.level)
        .gymName(this.gymName)
        .isSuccess(this.isSuccess)
        .color(this.color)
        .member(member)
        .s3URL(s3URL).build();
    }

}


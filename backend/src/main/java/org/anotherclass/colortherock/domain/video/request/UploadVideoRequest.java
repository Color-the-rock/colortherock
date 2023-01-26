package org.anotherclass.colortherock.domain.video.request;

import lombok.*;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.springframework.web.multipart.MultipartFile;

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
    @NotNull
    private MultipartFile newVideo;
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

    public Video toEntity() {
        Video video = new Video();
        video.setShootingDate(this.shootingDate);
        video.setLevel(this.level);
        video.setGymName(this.gymName);
        video.setIsSuccess(this.isSuccess);
        video.setColor(this.color);
        video.setMember(this.member);
        video.setVideoName(this.videoName);
        return video;
    }

    public Video toEntity(Member member, String s3URL) {
        Video video = new Video();
        video.setShootingDate(this.shootingDate);
        video.setLevel(this.level);
        video.setGymName(this.gymName);
        video.setIsSuccess(this.isSuccess);
        video.setColor(this.color);
        video.setMember(member);
        video.setS3URL(s3URL);
        return video;
    }

}


package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalSuccessVideoUploadRequest {

    @NotNull
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime writtenTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shootingTime;

    @NotNull
    private Integer level;

    @NotNull
    private String color;

    @NotNull
    private String gymName;

    public Video toEntity(Member member, String s3URL, LocalSuccessVideoUploadRequest request) {
        return Video.builder()
                .shootingDate(request.getShootingTime())
                .level(request.getLevel())
                .gymName(request.getGymName())
                .isSuccess(true)
                .color(request.getColor())
                .member(member)
                .s3URL(s3URL).build();
    }

}

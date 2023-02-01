package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.global.common.BaseTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalSuccessVideoUploadRequest extends BaseTime {

    @NotNull
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shootingTime;

    @NotNull
    private Integer level;

    @NotNull
    private String color;

    @NotNull
    private String gymName;

    public Video toEntity(Member member, String s3URL, String thumbnailURL, LocalSuccessVideoUploadRequest request) {
        return Video.builder()
                .shootingDate(request.getShootingTime())
                .level(request.getLevel())
                .gymName(request.getGymName())
                .isSuccess(true)
                .color(request.getColor())
                .member(member)
                .s3URL(s3URL)
                .thumbnailURL(thumbnailURL)
                .build();
    }

}

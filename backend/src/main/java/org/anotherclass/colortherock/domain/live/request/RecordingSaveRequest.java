package org.anotherclass.colortherock.domain.live.request;

import lombok.Data;
import lombok.Getter;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data @Getter
public class RecordingSaveRequest {

    @NotNull
    private String recordingId;
    @NotNull
    private Boolean isSaved;

    @Positive
    private Integer level;
    private String title;
    private String gymName;
    private Boolean isSuccess;
    private String color;

    public Video toEntity(String s3Url, String thumbnailUrl, Member member) {
        return Video.builder()
                .s3URL(s3Url)
                .thumbnailURL(thumbnailUrl)
                .member(member)
                .color(this.color)
                .gymName(this.gymName)
                .isSuccess(this.isSuccess)
                .level(this.level).build();
    }

}

package org.anotherclass.colortherock.domain.live.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Schema(description = "녹화 저장 요청")
public class RecordingSaveRequest {

    @NotNull
    @Schema(description = "녹화 영상 id")
    private String recordingId;
    @NotNull
    @Schema(description = "저장 여부")
    private Boolean isSaved;

    @Positive
    @Schema(description = "레벨")
    private Integer level;

    @NotBlank
    @Schema(description = "녹화 암장 이름")
    private String gymName;

    @NotNull
    @Schema(description = "성공 여부")
    private Boolean isSuccess;

    @NotBlank
    @Schema(description = "색깔")
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

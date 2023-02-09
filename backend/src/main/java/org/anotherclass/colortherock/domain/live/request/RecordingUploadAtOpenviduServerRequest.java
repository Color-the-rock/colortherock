package org.anotherclass.colortherock.domain.live.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.video.entity.Video;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Schema(description = "오픈비두 서버에 녹화 저장 요청")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class RecordingUploadAtOpenviduServerRequest {

    @NotNull
    @Schema(description = "녹화 영상 id")
    private String recordingId;
    @Positive
    @Schema(description = "레벨")
    private Integer level;

    @NotBlank
    @Schema(description = "녹화 제목")
    private String title;
    @NotBlank
    @Schema(description = "녹화 암장 이름")
    private String gymName;

    @NotNull
    @Schema(description = "성공 여부")
    private Boolean isSuccess;

    @NotBlank
    @Schema(description = "색깔")
    private String color;

    @NotBlank
    @Schema(description = "검증된 멤버 id")
    private Long memberId;

    public RecordingUploadAtOpenviduServerRequest(RecordingSaveRequest request, Long id) {
        this.recordingId = request.getRecordingId();
        this.color = request.getColor();
        this.memberId = id;
        this.gymName = request.getGymName();
        this.level = request.getLevel();
        this.isSuccess = request.getIsSuccess();
        this.title = request.getTitle();
    }

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

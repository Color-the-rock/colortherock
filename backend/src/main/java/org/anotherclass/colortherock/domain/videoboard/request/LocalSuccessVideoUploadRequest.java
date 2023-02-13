package org.anotherclass.colortherock.domain.videoboard.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.anotherclass.colortherock.global.common.BaseTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "영상 게시글에서 내 로컬에 있는 영상 올리는 요청")
public class LocalSuccessVideoUploadRequest extends BaseTime {

    @NotNull
    @Schema(description = "제목")
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "찍은 날짜")
    private LocalDate shootingTime;

    @NotNull
    @Schema(description = "레벨")
    private Integer level;

    @NotNull
    @Schema(description = "색깔")
    private String color;
    @Schema(description = "암장 이름")
    @NotNull
    private String gymName;

}

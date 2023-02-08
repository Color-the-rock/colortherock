package org.anotherclass.colortherock.domain.video.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@Schema(description = "내 영상 요청 ")
public class MyVideoRequest {
    @Positive
    @NotNull
    @Schema(description = "페이지네이션용 video id")
    private Long videoId;
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "찍은 날짜")
    private LocalDate shootingDate;
    @NotNull
    @Schema(description = "성공 여부")
    private Boolean isSuccess;

    @Builder
    public MyVideoRequest(Long videoId, LocalDate shootingDate, Boolean isSuccess) {
        this.videoId = videoId;
        this.shootingDate = shootingDate;
        this.isSuccess = isSuccess;
    }
}

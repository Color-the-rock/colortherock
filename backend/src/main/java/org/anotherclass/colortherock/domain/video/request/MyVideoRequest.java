package org.anotherclass.colortherock.domain.video.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class MyVideoRequest {
    @NotNull @Positive
    private Long videoId;
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shootingDate;
    @NotNull
    private Boolean isSuccess;

    @Builder
    public MyVideoRequest(Long videoId, LocalDate shootingDate, Boolean isSuccess) {
        this.videoId = videoId;
        this.shootingDate = shootingDate;
        this.isSuccess = isSuccess;
    }
}

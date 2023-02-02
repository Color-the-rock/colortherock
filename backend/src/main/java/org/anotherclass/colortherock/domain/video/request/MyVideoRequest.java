package org.anotherclass.colortherock.domain.video.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyVideoRequest {
    @NotNull @Positive
    private Long videoId;
    @NotNull @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate shootingDate;
    @NotNull
    private Boolean isSuccess;
}

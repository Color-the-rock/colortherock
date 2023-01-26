package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Getter
public class SuccessVideoUploadRequest {

    @NotNull
    Long videoId;

    @NotNull
    String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime writtenTime;
}

package org.anotherclass.colortherock.domain.videocomment.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentRequest {

    @NotNull
    private Long videoBoardId;
    @NotNull
    private String content;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime writtenTime;


}

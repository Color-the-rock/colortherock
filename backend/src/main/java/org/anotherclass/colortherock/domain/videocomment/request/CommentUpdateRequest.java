package org.anotherclass.colortherock.domain.videocomment.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateRequest {

    @NotNull
    private Long commentId;
    @NotNull
    private String content;

}

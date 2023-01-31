package org.anotherclass.colortherock.domain.videocomment.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentRequest {

    @NotNull
    private Long videoBoardId;
    @NotNull
    private String content;


}

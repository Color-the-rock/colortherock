package org.anotherclass.colortherock.domain.videocomment.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListRequest {

    private Long storeId;
    @NotNull
    private Long videoBoardId;


}

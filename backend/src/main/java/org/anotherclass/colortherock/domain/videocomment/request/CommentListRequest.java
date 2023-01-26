package org.anotherclass.colortherock.domain.videocomment.request;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
@Getter
public class CommentListRequest {

    private Long storeId;
    @NotNull
    private Long videoBoardId;


}

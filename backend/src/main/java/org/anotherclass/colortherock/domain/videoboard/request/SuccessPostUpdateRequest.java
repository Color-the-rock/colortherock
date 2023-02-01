package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
public class SuccessPostUpdateRequest {

    @NotNull
    private Long videoBoardId;
    @NotNull
    private String title;

}

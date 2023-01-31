package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SuccessVideoUploadRequest {

    @NotNull
    Long videoId;

    @NotNull
    String title;
}

package org.anotherclass.colortherock.domain.videoboard.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessVideoUploadRequest {

    @NotNull
    Long videoId;

    @NotNull
    String title;
}

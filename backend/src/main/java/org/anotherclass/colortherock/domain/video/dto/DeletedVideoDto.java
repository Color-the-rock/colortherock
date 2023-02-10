package org.anotherclass.colortherock.domain.video.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeletedVideoDto {

    @Schema(description = "영상 제목")
    private String videoName;
    @Schema(description = "영상 성공여부")
    private Boolean isVideoSuccess;

}

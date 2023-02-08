package org.anotherclass.colortherock.domain.videoboard.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "영상 게시글 응답")
public class VideoBoardDetailResponse {
    @Schema(description = "영상 게시글 id")
    private Long videoBoardId;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "영상 URL")
    private String s3URL;
    @Schema(description = "글 쓴 시간")
    private LocalDate createdDate;

}

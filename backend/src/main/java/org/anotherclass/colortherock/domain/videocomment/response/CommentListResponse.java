package org.anotherclass.colortherock.domain.videocomment.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CommentListResponse {

    private Long commentId;
    private String nickname;
    private String content;
    private LocalDateTime writtenTime;
}

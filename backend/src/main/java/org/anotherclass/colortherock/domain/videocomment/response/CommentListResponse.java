package org.anotherclass.colortherock.domain.videocomment.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListResponse {

    private Long commentId;
    private String nickname;
    private String content;
    private LocalDateTime writtenTime;
}

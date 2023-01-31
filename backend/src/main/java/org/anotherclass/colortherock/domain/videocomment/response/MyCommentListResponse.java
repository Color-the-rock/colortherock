package org.anotherclass.colortherock.domain.videocomment.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyCommentListResponse {
    private Long commentId;
    private Long videoBoardId;
    private String nickname;
    private String content;
    private LocalDateTime createdDate;

}

package org.anotherclass.colortherock.domain.videocomment.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyCommentListResponse {
    private Long commentId;
    private Long videoBoardId;
    private String title;
    private String content;
    private LocalDate createdDate;

}

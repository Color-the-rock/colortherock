package org.anotherclass.colortherock.domain.report.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminReportedPostResponse {
    private Long videoBoardId;
    private String title;

}

package org.anotherclass.colortherock.domain.report.response;

import lombok.*;
import org.anotherclass.colortherock.domain.report.entity.Report;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminReportDetailResponse {
    private Report.Category category;
    private Long memberId;

}

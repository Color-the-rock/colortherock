package org.anotherclass.colortherock.domain.report.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.anotherclass.colortherock.domain.report.entity.Report;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "신고된 게시글의 상세 내역")
public class AdminReportDetailResponse {

    @Schema(description = "신고 종류")
    private Report.Category category;

    @Schema(description = "멤버 id")
    private Long memberId;

}

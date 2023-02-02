package org.anotherclass.colortherock.domain.report.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostReportRequest {
    @NotNull
    private Long videoBoardId;
    @NotNull
    private String category;

}

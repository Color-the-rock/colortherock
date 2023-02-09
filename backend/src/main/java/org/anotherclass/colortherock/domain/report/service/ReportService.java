package org.anotherclass.colortherock.domain.report.service;

import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.report.request.PostReportRequest;

public interface ReportService {
    void reportPost(Member member, PostReportRequest request);
    Long checkReportNum(Long videoBoardId);
}

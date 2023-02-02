package org.anotherclass.colortherock.domain.report.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.anotherclass.colortherock.domain.report.entity.QReport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReportReadRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public ReportReadRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    QReport report = QReport.report;

    public Long countReport(Long videoBoardId) {
        List<Long> result = query.selectFrom(report)
                .where(report.videoBoard.id.eq(videoBoardId))
                .select(report.member.id.countDistinct())
                .fetch();

        return result.get(0);
    }

}

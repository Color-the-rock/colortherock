package org.anotherclass.colortherock.domain.report.repository;

import org.anotherclass.colortherock.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT COUNT(DISTINCT r.member.id) " +
            "FROM Report r " +
            "WHERE r.videoBoard.id = :videoBoardId")
    Long countReport(@Param("videoBoardId") Long videoBoardId);
}

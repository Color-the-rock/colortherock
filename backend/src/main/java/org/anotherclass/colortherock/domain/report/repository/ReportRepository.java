package org.anotherclass.colortherock.domain.report.repository;

import org.anotherclass.colortherock.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByVideoBoardId(Long videoBoardId);
    void deleteAllByVideoBoardId(Long videoBoardId);
}

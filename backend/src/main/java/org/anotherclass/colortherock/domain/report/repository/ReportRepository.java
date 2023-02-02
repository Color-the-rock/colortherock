package org.anotherclass.colortherock.domain.report.repository;

import org.anotherclass.colortherock.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

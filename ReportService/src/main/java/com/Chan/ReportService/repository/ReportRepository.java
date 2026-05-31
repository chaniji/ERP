package com.Chan.ReportService.repository;

import com.Chan.ReportService.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

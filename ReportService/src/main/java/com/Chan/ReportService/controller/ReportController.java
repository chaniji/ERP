package com.Chan.ReportService.controller;

import com.Chan.ReportService.model.Report;
import com.Chan.ReportService.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @QueryMapping
    public List<Report> allReports() {
        return reportRepository.findAll();
    }

    @QueryMapping
    public Report reportById(@Argument Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Report createReport(@Argument String title, @Argument String content) {
        Report report = new Report();
        report.setTitle(title);
        report.setContent(content);
        return reportRepository.save(report);
    }
}

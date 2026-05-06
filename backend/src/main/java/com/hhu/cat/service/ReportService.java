package com.hhu.cat.service;

import com.hhu.cat.entity.Report;
import com.hhu.cat.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Optional<Report> findById(Integer id) {
        return reportRepository.findById(id);
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public Report update(Integer id, Report reportDetails) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("上报记录不存在, ID: " + id));
        report.setStatus(reportDetails.getStatus());
        report.setReviewedBy(reportDetails.getReviewedBy());
        report.setReviewedAt(reportDetails.getReviewedAt());
        return reportRepository.save(report);
    }

    public void delete(Integer id) {
        reportRepository.deleteById(id);
    }
}
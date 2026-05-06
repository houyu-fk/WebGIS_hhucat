package com.hhu.cat.controller;

import com.hhu.cat.entity.Report;
import com.hhu.cat.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {
    @Autowired
    private ReportService reportService;

    // 获取所有上报记录
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.findAll());
    }

    // 根据ID获取上报记录
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Integer id) {
        return reportService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 新增上报（前端传 userId, longitude, latitude 等）
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report savedReport = reportService.save(report);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
    }

    // 审核上报（修改状态、审核人ID）
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Integer id, @RequestBody Report report) {
        return ResponseEntity.ok(reportService.update(id, report));
    }

    // 删除上报
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        reportService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
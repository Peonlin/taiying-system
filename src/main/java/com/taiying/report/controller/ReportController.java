package com.taiying.report.controller;

import com.taiying.report.dto.ReportDTO;
import com.taiying.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/report")
    public String insertReport(@CookieValue("uid")String uid, @RequestBody ReportDTO reportDTO) throws Exception {
        reportService.insertReport(uid, reportDTO);
        return "success";
    }

    @GetMapping("/report")
    public List<ReportDTO> queryReports(@CookieValue("uid")String uid) throws Exception {
        return reportService.queryReports(uid, null);
    }

    @GetMapping("/report/{reportId}")
    public ReportDTO queryReportById(@PathVariable String reportId) throws Exception {
        List<ReportDTO> list = reportService.queryReports(null, reportId);
        return (list == null || list.size() == 0) ? null : list.get(0);
    }

    @PutMapping("/report/{reportId}")
    public String updateReportInfo(@PathVariable String reportId, @CookieValue("uid") String uid, @RequestBody ReportDTO reportDTO) throws Exception {
        reportDTO.setReportId(reportId);
        reportService.updateReport(reportDTO);
        return "success";
    }
}

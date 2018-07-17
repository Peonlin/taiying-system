package com.taiying.report.controller;

import com.taiying.report.dto.ReportDTO;
import com.taiying.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> queryReports(@CookieValue("uid")String uid, String phone, String pageNo, String startDate, String endDate) throws Exception {
        Map<String, Object> map = new HashMap<>(2);
        map.put("list", reportService.queryReports(uid, null, phone, pageNo, startDate, endDate));
        map.put("size", reportService.queryReportSize(uid, null, phone, pageNo, startDate, endDate));
        return map;
    }

    @GetMapping("/report/{reportId}")
    public ReportDTO queryReportById(@PathVariable String reportId) throws Exception {
        List<ReportDTO> list = reportService.queryReports(null, reportId, null, null, null, null);
        return (list == null || list.size() == 0) ? null : list.get(0);
    }

    @PutMapping("/report/{reportId}")
    public String updateReportInfo(@PathVariable String reportId, @CookieValue("uid") String uid, @RequestBody ReportDTO reportDTO) throws Exception {
        reportDTO.setReportId(reportId);
        reportService.updateReport(uid, reportDTO);
        return "success";
    }

    @PutMapping("/agent")
    public String setAgent(@RequestBody ReportDTO reportDTO) throws Exception {
        reportService.setAgent(reportDTO.getAgentId(), reportDTO.getReportId());
        return "success";
    }

    @PutMapping("/confirm")
    public void confirm(@RequestBody ReportDTO reportDTO) throws Exception {
        reportService.confirm(reportDTO.getReportId(), reportDTO.getConfirmFlag());
    }

    @PostMapping("/natural")
    public String natural(@RequestBody ReportDTO reportDTO) throws Exception {
        reportService.natural(reportDTO);
        return "success";
    }
}

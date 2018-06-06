package com.taiying.report.controller;

import com.taiying.report.dto.ReportDTO;
import com.taiying.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/report")
    public String insertReport(@CookieValue("uid")String uid, @RequestBody ReportDTO reportDTO) {
        try {
            reportService.insertReport(reportDTO);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }
}

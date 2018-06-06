package com.taiying.report.service;

import com.taiying.report.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    void insertReport(String uid, ReportDTO reportDTO) throws Exception;

    List<ReportDTO> queryReports(String uid, String reportId) throws Exception;

    void updateReport(ReportDTO reportDTO) throws Exception;
}

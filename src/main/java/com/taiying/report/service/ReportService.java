package com.taiying.report.service;

import com.taiying.report.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    void insertReport(String uid, ReportDTO reportDTO) throws Exception;

    List<ReportDTO> queryReports(String uid, String reportId, String phone, String pageNo, String startDate, String endDate) throws Exception;

    Integer queryReportSize(String uid, String reportId, String phone, String pageNo, String startDate, String endDate);

    void updateReport(String uid, ReportDTO reportDTO) throws Exception;

    void setAgent(String agentId, String reportId) throws Exception;

    void confirm(String reportId, String confirmFlag) throws Exception;

    void natural(ReportDTO reportDTO) throws Exception;
}

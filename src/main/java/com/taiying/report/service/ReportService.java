package com.taiying.report.service;

import com.taiying.report.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    void insertReport(String uid, ReportDTO reportDTO) throws Exception;

    List<ReportDTO> queryReports(String uid, String reportId, String phone) throws Exception;

    void updateReport(String uid, ReportDTO reportDTO) throws Exception;

    void setAgent(String agentId, String reportId) throws Exception;

    void confirm(String reportId, String confirmFlag) throws Exception;
}

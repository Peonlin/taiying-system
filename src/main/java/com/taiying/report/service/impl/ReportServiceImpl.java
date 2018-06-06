package com.taiying.report.service.impl;

import com.taiying.report.dao.ReportDAO;
import com.taiying.report.dto.ReportDTO;
import com.taiying.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDAO reportDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertReport(ReportDTO reportDTO) throws Exception {
        reportDAO.insertReport(reportDTO);
    }
}

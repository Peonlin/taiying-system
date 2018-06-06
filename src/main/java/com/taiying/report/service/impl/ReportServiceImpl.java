package com.taiying.report.service.impl;

import com.taiying.common.util.CacheUtil;
import com.taiying.report.dao.ReportDAO;
import com.taiying.report.dto.ReportDTO;
import com.taiying.report.service.ReportService;
import com.taiying.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDAO reportDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertReport(String uid, ReportDTO reportDTO) throws Exception {
        UserDTO userDTO = CacheUtil.getUser(uid);
        reportDTO.setCustomerFirstName(userDTO.getName().substring(0, 1));
        reportDTO.setUid(uid);
        reportDAO.insertReport(reportDTO);
    }

    @Override
    public List<ReportDTO> queryReports(String uid, String reportId) throws Exception {
        return StringUtils.isNotBlank(uid) ? reportDAO.queryReports(uid) : reportDAO.queryReportsByReportId(reportId);
    }

    @Override
    public void updateReport(ReportDTO reportDTO) throws Exception {
        Integer i = reportDAO.validNewCustomer(reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(), reportDTO.getCustomerPhone3(), reportDTO.getCustomerPhone4());
        if (i == null) {
            i = reportDAO.addNewCustomer(reportDTO.getCustomerName(), reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(),
                    reportDTO.getCustomerPhone().substring(0, 3), reportDTO.getCustomerPhone().substring(7, 11), reportDTO.getSex());
        } else {
            reportDAO.updateCustomer(i);
        }
        reportDTO.setCustomerId(String.valueOf(i));
        reportDAO.updateReport(reportDTO);
    }
}

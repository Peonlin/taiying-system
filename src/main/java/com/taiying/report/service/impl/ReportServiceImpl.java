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
        Integer i = reportDAO.validNewCustomer(reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(), reportDTO.getCustomerPhone3(), reportDTO.getCustomerPhone4());
        UserDTO userDTO = CacheUtil.getUser(uid);
        if (i == null) {
            i = reportDAO.addNewCustomer(reportDTO.getCustomerName(), reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(),
                    reportDTO.getCustomerPhone().substring(0, 3), reportDTO.getCustomerPhone().substring(7, 11), reportDTO.getSex(), userDTO.getCompany());
            reportDTO.setCustomerId(String.valueOf(i));
            reportDTO.setCustomerFirstName(userDTO.getName().substring(0, 1));
            reportDTO.setUid(uid);
            reportDAO.insertReport(reportDTO);
        } else {
            Integer j = reportDAO.judgeValidCustomer(uid, i);
            if (j > 0) {
                reportDTO.setCustomerId(String.valueOf(i));
                reportDTO.setCustomerFirstName(userDTO.getName().substring(0, 1));
                reportDTO.setUid(uid);
                reportDAO.insertReport(reportDTO);
            } else {
                throw new Exception("报备不符合要求");
            }
        }
    }

    @Override
    public List<ReportDTO> queryReports(String uid, String reportId, String phone) throws Exception {
        UserDTO u = CacheUtil.getUser(uid);
        return StringUtils.isNotBlank(uid) ? reportDAO.queryReports(uid, phone, u.getRole()) : reportDAO.queryReportsByReportId(reportId);
    }

    @Override
    public void updateReport(String uid, ReportDTO reportDTO) throws Exception {
        Integer i = reportDAO.validNewCustomer(reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(), reportDTO.getCustomerPhone3(), reportDTO.getCustomerPhone4());
        UserDTO u = CacheUtil.getUser(uid);
        if (i == null) {
            i = reportDAO.addNewCustomer(reportDTO.getCustomerName(), reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(),
                    reportDTO.getCustomerPhone().substring(0, 3), reportDTO.getCustomerPhone().substring(7, 11), reportDTO.getSex(), u.getCompany());
        } else {
            reportDAO.updateCustomer(i, u.getCompany(), reportDTO.getCustomerName(), reportDTO.getCustomerPhone());
        }
        reportDTO.setCustomerId(String.valueOf(i));
        reportDAO.updateReport(reportDTO);
    }

    @Override
    public void setAgent(String agentId, String reportId) throws Exception {
        Integer i = reportDAO.queryCustomerId(Integer.valueOf(reportId));
        reportDAO.updateCustomerForAgent(i, agentId);
    }

    @Override
    public void confirm(String reportId, String confirmFlag) throws Exception {
        reportDAO.confirmReport(confirmFlag, Integer.valueOf(reportId));
    }
}

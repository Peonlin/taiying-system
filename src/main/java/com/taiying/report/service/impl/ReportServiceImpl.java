package com.taiying.report.service.impl;

import com.taiying.common.util.CacheUtil;
import com.taiying.report.dao.ReportDAO;
import com.taiying.report.dto.CustomerDTO;
import com.taiying.report.dto.ReportDTO;
import com.taiying.report.service.ReportService;
import com.taiying.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDAO reportDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertReport(String uid, ReportDTO reportDTO) throws Exception {
        try {
            String i = reportDAO.validNewCustomer(reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(),
                    StringUtils.isBlank(reportDTO.getCustomerPhone())? reportDTO.getCustomerPhone3() : reportDTO.getCustomerPhone().substring(0, 3),
                    StringUtils.isBlank(reportDTO.getCustomerPhone())? reportDTO.getCustomerPhone4() : reportDTO.getCustomerPhone().substring(7, 11));
            UserDTO userDTO = CacheUtil.getUser(uid);
            if (i == null) {
                i = UUID.randomUUID().toString().replaceAll("-", "");
                CustomerDTO d = new CustomerDTO(reportDTO.getCustomerName(), i, reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(),
                        StringUtils.isBlank(reportDTO.getCustomerPhone())? reportDTO.getCustomerPhone3() : reportDTO.getCustomerPhone().substring(0, 3),
                        StringUtils.isBlank(reportDTO.getCustomerPhone())? reportDTO.getCustomerPhone4() : reportDTO.getCustomerPhone().substring(7, 11), reportDTO.getSex(), userDTO.getCompany());
                reportDAO.addNewCustomer(d);
                reportDTO.setCustomerId(i);
                reportDTO.setCustomerFirstName(reportDTO.getCustomerName().substring(0, 1));
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
        } catch (Exception e) {
            throw new Exception("报备不符合要求");
        }

    }

    @Override
    public List<ReportDTO> queryReports(String uid, String reportId, String phone) throws Exception {
        UserDTO u = null;
        try {
            u = CacheUtil.getUser(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.isNotBlank(uid) ? reportDAO.queryReports(uid, phone, u.getRole()) : reportDAO.queryReportsByReportId(reportId);
    }

    @Override
    public void updateReport(String uid, ReportDTO reportDTO) throws Exception {
        String i = reportDAO.queryCustomerId(Integer.valueOf(reportDTO.getReportId()));
        UserDTO u = CacheUtil.getUser(uid);
        reportDAO.updateCustomer(i, u.getCompany(), reportDTO.getCustomerName(), reportDTO.getCustomerPhone());
        reportDTO.setCustomerId(i);
        reportDAO.updateReport(reportDTO);
    }

    @Override
    public void setAgent(String agentId, String reportId) throws Exception {
        String i = reportDAO.queryCustomerId(Integer.valueOf(reportId));
        reportDAO.updateCustomerForAgent(i, agentId);
    }

    @Override
    public void confirm(String reportId, String confirmFlag) throws Exception {
        reportDAO.confirmReport(confirmFlag, Integer.valueOf(reportId));
    }

    @Override
    public void natural(ReportDTO reportDTO) throws Exception {
        String i = reportDAO.validNewCustomer(reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(), reportDTO.getCustomerPhone3(), reportDTO.getCustomerPhone4());
        reportDTO.setActualVisitDate(LocalTime.now().toString());
        if (i == null) {
            i = UUID.randomUUID().toString().replaceAll("-", "");
            CustomerDTO d = new CustomerDTO(reportDTO.getCustomerName(), i, reportDTO.getCustomerName().substring(0, 1), reportDTO.getCustomerPhone(),
                    StringUtils.isBlank(reportDTO.getCustomerPhone())? reportDTO.getCustomerPhone3() : reportDTO.getCustomerPhone().substring(0, 3),
                    StringUtils.isBlank(reportDTO.getCustomerPhone())? reportDTO.getCustomerPhone4() : reportDTO.getCustomerPhone().substring(7, 11), reportDTO.getSex(), null);
            reportDAO.addNewCustomer(d);
            reportDTO.setCustomerId(i);
        } else {
            reportDAO.updateCustomer(i, null, reportDTO.getCustomerName(), reportDTO.getCustomerPhone());
        }
        reportDAO.insertReport(reportDTO);
    }

    public static void main(String[] args) throws Exception {
        Map<String,Object> map = new HashMap<>();
        File oldFile = new File("C:\\Users\\Sauce\\Desktop\\test6.csv");
        FileInputStream fis = new FileInputStream(oldFile);
        InputStreamReader sr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(sr);
        String line;
        File newFile1 = new File("C:\\Users\\Sauce\\Desktop\\output4.sql");
        FileOutputStream fos1 = new FileOutputStream(newFile1);
        OutputStreamWriter osw1 = new OutputStreamWriter(fos1);
        BufferedWriter bw1 = new BufferedWriter(osw1);
        File newFile = new File("C:\\Users\\Sauce\\Desktop\\outputReport4.sql");
        FileOutputStream fos = new FileOutputStream(newFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        while ((line = br.readLine()) != null) {
            String []arr = line.replaceAll(" ", "").split(",");
            if (arr.length == 2) {
                String sex = "F";
                String name = arr[0];
                String phone = arr[1];
                String firstName = null;
                String phone3 = null;
                String phone4 = null;
                if (name.contains("生") || name.contains("哥") || name.contains("老师") || name.contains("总") || name.contains("政委")) {
                    if (name.startsWith("(")) {
                        name = name.substring(0,4);
                        firstName = name.substring(3,4);
                    } else {
                        name = name.substring(0, 1);
                        firstName = name;
                    }
                } else if (name.contains("姐") || name.contains("女") || name.contains("太太") || name.contains("姨")) {
                    if (name.startsWith("(")) {
                        name = name.substring(0,4);
                        firstName = name.substring(3,4);
                    } else {
                        name = name.substring(0, 1);
                        firstName = name;
                    }
                    sex = "M";
                } else {
                    firstName = name.substring(0, 1);
                }
                if (phone.length() == 11) {
                    phone3 = phone.substring(0, 3);
                    phone4 = phone.substring(7, 11);
                    String key = firstName + phone3 + phone4;
                    String value = phone4;
                    if (!map.containsKey(key)) {
                        map.put(key, value);
                        String id = UUID.randomUUID().toString().replaceAll("-", "");
                        bw1.write("insert into ty_customer(customer_id, customer_name, phone, sex, customer_first_name, phone3, phone4) values " +
                                "('" +id + "', '" + name + "'" +
                                ", '" + phone + "', '" + sex + "', '" + firstName + "', '" + phone3 + "', '" + phone4 + "');\n");
                        bw.write("insert into ty_report (customer_id, customer_phone, customer_phone_3, customer_phone_4,customer_name, customer_first_name, confirm_flag, sex)\n" +
                                "    VALUES " +
                                "('" + id + "', '" + phone + "'" +
                                ", '" + phone3 + "', '" + phone4 + "', '" + name + "', '" + firstName + "', 'Y',  '" + sex + "');\n");
                    }
                }
            }
        }
        bw.flush();
        br.close();
        bw1.flush();
        bw1.close();
        bw.close();
    }
}

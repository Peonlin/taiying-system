package com.taiying.report.controller;

import com.taiying.report.dto.ReportDTO;
import com.taiying.report.service.ReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/export")
    public void exportReport(@CookieValue("uid")String uid, String phone, String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = reportService.exportReport(uid, phone, null, startDate, endDate);
        try {
            this.setResponseHeader(response, "导出列表.xls");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/agentType")
    public List<String> queryAgentType() {
        String []a = {"a", "b", "c", "d", "e", "f", "g"};
        return Arrays.stream(a).collect(Collectors.toList());
    }

    @PutMapping("/agentType")
    public String setAgentType(@RequestBody ReportDTO reportDTO) throws Exception {
        reportService.setAgentType(reportDTO);
        return "success";
    }
}

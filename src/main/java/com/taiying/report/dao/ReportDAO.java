package com.taiying.report.dao;

import com.taiying.report.dto.CustomerDTO;
import com.taiying.report.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportDAO {

    void insertReport(ReportDTO reportDTO);

    List<ReportDTO> queryReports(@Param("uid") String uid, @Param("phone") String phone, @Param("role") String role, @Param("first") Integer first, @Param("last")Integer last,
    @Param("company")String company, @Param("startDate")String startDate, @Param("endDate") String endDate);

    List<ReportDTO> queryReportsByReportId(@Param("reportId") String reportId);

    void updateReport(ReportDTO reportDTO);

    String validNewCustomer(@Param("firstName")String firstName, @Param("phone")String phone, @Param("phone3") String phone3, @Param("phone4") String phone4);

    void addNewCustomer(CustomerDTO d);

    void updateCustomer(@Param("id")String id, @Param("company") String company, @Param("name") String name, @Param("phone")String phone);

    Integer judgeValidCustomer(@Param("uid")String uid, @Param("customerId") String customerId);

    String queryCustomerId(@Param("reportId") Integer reportId);

    void updateCustomerForAgent(@Param("id")String id, @Param("agentId") String agentId);

    void confirmReport(@Param("confirmFlag") String confirmFlag, @Param("reportId") Integer reportId);

    Integer queryReportSize(@Param("uid") String uid, @Param("phone") String phone, @Param("role") String role, @Param("first") Integer first, @Param("last")Integer last,
    @Param("company")String company, @Param("startDate")String startDate, @Param("endDate") String endDate);
}

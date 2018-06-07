package com.taiying.report.dao;

import com.taiying.report.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportDAO {

    void insertReport(ReportDTO reportDTO);

    List<ReportDTO> queryReports(@Param("uid") String uid, @Param("phone") String phone, @Param("role") String role);

    List<ReportDTO> queryReportsByReportId(@Param("reportId") String reportId);

    void updateReport(ReportDTO reportDTO);

    Integer validNewCustomer(@Param("firstName")String firstName, @Param("phone")String phone, @Param("phone3") String phone3, @Param("phone4") String phone4);

    Integer addNewCustomer(@Param("customerName")String customerName, @Param("firstName")String firstName, @Param("phone")String phone, @Param("phone3") String phone3,
                        @Param("phone4") String phone4, @Param("sex") String sex, @Param("company") String company);

    void updateCustomer(@Param("id")Integer id, @Param("company") String company, @Param("name") String name, @Param("phone")String phone);

    Integer judgeValidCustomer(@Param("uid")String uid, @Param("customerId") Integer customerId);

    Integer queryCustomerId(@Param("reportId") Integer reportId);

    void updateCustomerForAgent(@Param("id")Integer id, @Param("agentId") String agentId);

    void confirmReport(@Param("confirmFlag") String confirmFlag, @Param("reportId") Integer reportId);
}

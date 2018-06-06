package com.taiying.report.dao;

import com.taiying.report.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportDAO {

    void insertReport(ReportDTO reportDTO);

    List<ReportDTO> queryReports(@Param("uid") String uid);

    List<ReportDTO> queryReportsByReportId(@Param("reportId") String reportId);

    void updateReport(ReportDTO reportDTO);

    Integer validNewCustomer(@Param("firstName")String firstName, @Param("phone")String phone, @Param("phone3") String phone3, @Param("phone4") String phone4);

    Integer addNewCustomer(@Param("customerName")String customerName, @Param("firstName")String firstName, @Param("phone")String phone, @Param("phone3") String phone3,
                        @Param("phone4") String phone4, @Param("sex") String sex);

    void updateCustomer(@Param("id")Integer id);
}

package com.taiying.report.dao;

import com.taiying.report.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDAO {

    void insertReport(ReportDTO reportDTO);
}

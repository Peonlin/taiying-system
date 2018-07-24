package com.taiying.common.util;

import com.taiying.report.dto.ReportDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class ExcelUtil {

    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param list 内容
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<ReportDTO> list){
//报备日期 是否到场 客户姓名 客户电话号码 来访人数 来访次数 新老客户 代理公司名称 业务员姓名 业务员电话号码 置业顾问 预约到访时间 实际到访时间
        String []title = {"报备日期", "是否到场", "客户姓名", "客户电话号码", "来访人数", "来访次数", "新老客户", "代理公司名称", "业务员姓名",
        "业务员电话号码", "置业顾问", "预约到访时间", "实际到访时间"};
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i = 0;i < title.length; i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<list.size();i++){
            row = sheet.createRow(i + 1);
            //报备日期 是否到场 客户姓名 客户电话号码 来访人数 来访次数 新老客户 代理公司名称 业务员姓名 业务员电话号码 置业顾问 预约到访时间 实际到访时间
            sheet.setDefaultColumnWidth(50);
            row.createCell(0).setCellValue(list.get(i).getReportDate());
            row.createCell(1).setCellValue(list.get(i).getConfirmFlag());
            row.createCell(2).setCellValue(list.get(i).getCustomerName() + (StringUtils.equals(list.get(i).getSex(),"M") ? "先生" : "小姐"));
            row.createCell(3).setCellValue((StringUtils.isBlank(list.get(i).getCustomerPhone()) ?
                    list.get(i).getCustomerPhone3() + "xxxx" + list.get(i).getCustomerPhone4() : list.get(i).getCustomerPhone()));
            row.createCell(4).setCellValue(list.get(i).getVisitNum());
            row.createCell(5).setCellValue(list.get(i).getVisitCount());
            row.createCell(6).setCellValue(list.get(i).getIsOld().equals("Y")? "老" : "新");
            row.createCell(7).setCellValue(list.get(i).getEmpCompany());
            row.createCell(8).setCellValue(list.get(i).getEmpName());
            row.createCell(9).setCellValue(list.get(i).getEmpPhone());
            row.createCell(10).setCellValue(list.get(i).getAgentName());
            row.createCell(11).setCellValue(list.get(i).getVisitDate());
            row.createCell(12).setCellValue(list.get(i).getActualVisitDate());
        }
        return wb;
    }
}
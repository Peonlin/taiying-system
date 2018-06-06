package com.taiying.report.dto;

import java.io.Serializable;

public class ReportDTO implements Serializable {

    private static final long serialVersionUID = 8247017584710501403L;

    private String reportId;

    private String customerId;

    private String customerPhone;

    private String customerPhone3;

    private String customerPhone4;

    private String visitNum;

    private String visitDate;

    private String actualVisitDate;

    private String confirmFlag;

    private String isOld;

    private String customerName;

    private String extraInfo;

    private String isValid;

    private String sex;

    private String customerFirstName;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPhone3() {
        return customerPhone3;
    }

    public void setCustomerPhone3(String customerPhone3) {
        this.customerPhone3 = customerPhone3;
    }

    public String getCustomerPhone4() {
        return customerPhone4;
    }

    public void setCustomerPhone4(String customerPhone4) {
        this.customerPhone4 = customerPhone4;
    }

    public String getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(String visitNum) {
        this.visitNum = visitNum;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getActualVisitDate() {
        return actualVisitDate;
    }

    public void setActualVisitDate(String actualVisitDate) {
        this.actualVisitDate = actualVisitDate;
    }

    public String getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public String getIsOld() {
        return isOld;
    }

    public void setIsOld(String isOld) {
        this.isOld = isOld;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }
}

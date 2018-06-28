package com.taiying.report.dto;

public class CustomerDTO {

    private String customerName;

    private String customerId;

    private String phone;

    private String sex;

    private String firstName;

    private String phone3;

    private String phone4;

    private String company;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone4() {
        return phone4;
    }

    public void setPhone4(String phone4) {
        this.phone4 = phone4;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CustomerDTO(String customerName, String customerId, String firstName, String phone, String phone3, String phone4, String sex, String company) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.phone = phone;
        this.sex = sex;
        this.firstName = firstName;
        this.phone3 = phone3;
        this.phone4 = phone4;
        this.company = company;
    }
}

package com.taiying.user.dto;

import java.io.Serializable;

public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = -3583354859319754458L;

    String companyId;

    String companyName;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

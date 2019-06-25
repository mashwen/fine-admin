package com.ant.shop.asorm.entity;

import java.io.Serializable;

public class FineStaffOrgRole extends FineStaffOrgRoleKey implements Serializable {
    private Boolean includeAll;

    private static final long serialVersionUID = 1L;

    public Boolean getIncludeAll() {
        return includeAll;
    }

    public void setIncludeAll(Boolean includeAll) {
        this.includeAll = includeAll;
    }
}
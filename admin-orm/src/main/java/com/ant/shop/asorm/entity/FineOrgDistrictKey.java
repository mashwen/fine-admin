package com.ant.shop.asorm.entity;

import java.io.Serializable;

public class FineOrgDistrictKey implements Serializable {
    private Integer orgId;

    private Integer districtId;

    private static final long serialVersionUID = 1L;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
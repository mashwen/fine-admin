package com.ant.shop.asorm.entity;

import java.io.Serializable;

public class FineDistrictAreaKey implements Serializable {
    private Integer districtId;

    private Integer areaId;

    private static final long serialVersionUID = 1L;

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
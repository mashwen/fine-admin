package com.ant.shop.asorm.entity;

import java.io.Serializable;

public class FineRoleResource implements Serializable {
    private Integer roleId;

    private Integer resourceId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}
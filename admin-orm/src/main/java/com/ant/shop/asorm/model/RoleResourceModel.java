package com.ant.shop.asorm.model;

import java.util.List;
import java.util.Map;

public class RoleResourceModel {
    private int roleId;
    private Map<Object, List> resource;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Map<Object, List> getResource() {
        return resource;
    }

    public void setResource(Map<Object, List> resource) {
        this.resource = resource;
    }
}

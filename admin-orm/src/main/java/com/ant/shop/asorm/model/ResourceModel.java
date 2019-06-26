package com.ant.shop.asorm.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResourceModel {
    private Integer id;

    private Integer resourceGroupId;

    private Integer parentId;

    private String name;

    private String url;

    private String label;

    private String remark;

    private byte type;

    private Short sort;

    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(Integer resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

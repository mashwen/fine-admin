package com.ant.shop.asorm.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResourceModel {
    private Integer id;
    @NotNull(message = "群组不能为空")
    private Integer resourceGroupId;
    @NotNull(message = "父级不能为空")
    private Integer parentId;

    private String name;
    @NotNull(message = "URL不能为空")
    private String url;

    private String label;

    private String remark;
    @NotNull(message = "类型不能为空")
    private byte type;
    @NotNull(message = "排序号不能为空")
    private Short sort;

    private Date created;

    private List subordinateResourceList;

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

    public List getSubordinateResourceList() {
        return subordinateResourceList;
    }

    public void setSubordinateResourceList(List subordinateResourceList) {
        this.subordinateResourceList = subordinateResourceList;
    }
}

package com.ant.shop.asorm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class FineRole implements Serializable {
    private Integer id;
    @NotNull(message = "角色名称不能为空")
    private String name;
    @NotNull(message = "排序号不能为空")
    private Short sort;

    private String remark;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
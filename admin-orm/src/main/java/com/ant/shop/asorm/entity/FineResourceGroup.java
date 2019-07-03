package com.ant.shop.asorm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FineResourceGroup implements Serializable {
    private Integer id;
    @NotNull(message = "资源组名称不能为空")
    private String name;
    @NotNull(message = "排序号不能为空")
    private Short sort;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


}
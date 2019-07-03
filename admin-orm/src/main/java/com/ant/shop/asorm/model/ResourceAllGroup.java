package com.ant.shop.asorm.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResourceAllGroup {
    private int id;
    private String name;
    private Short sort;
    private List resource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public List getResource() {
        return resource;
    }

    public void setResource(List resource) {
        this.resource = resource;
    }
}

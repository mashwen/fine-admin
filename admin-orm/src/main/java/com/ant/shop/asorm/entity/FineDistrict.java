package com.ant.shop.asorm.entity;

import com.ant.shop.asorm.model.AddOrganizationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FineDistrict implements Serializable {
    private Integer id;

    @NotNull(message = "区域名称不能为空！",groups = AddOrganizationDTO.AddDistrictCheck.class)
    private String name;

    @NotNull(message = "区域级别不能为空！",groups = AddOrganizationDTO.AddDistrictCheck.class)
    private Byte level;

    @NotNull(message = "上级区域不能为空！",groups = AddOrganizationDTO.AddDistrictCheck.class)
    private Integer parentId;

    private String postcode;

    private String phoneticName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getPhoneticName() {
        return phoneticName;
    }

    public void setPhoneticName(String phoneticName) {
        this.phoneticName = phoneticName == null ? null : phoneticName.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
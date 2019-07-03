package com.ant.shop.asorm.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StaffModel {
    private int id;
    @NotNull(message = "姓名不能为空")
    private String fullname;
    @NotNull(message = "性别不能为空")
    private Byte gender;

    private String jobTitle;

    private String mobile;

    private String email;
    @NotNull(message = "密码不能为空")
    private String password;

    private String avatar;

    private Map<Object, List> orgRole;

    private Byte status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Object, List> getOrgRole() {
        return orgRole;
    }

    public void setOrgRole(Map<Object, List> orgRole) {
        this.orgRole = orgRole;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}

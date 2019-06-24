package com.ant.shop.asorm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * 增加字段 所需要的model
 * @Author liuzongqiang
 * @Date 2019/6/24 0024 14:11
 * @Version 1.0
 **/
@Data
@ToString
public class FineAdminFieldDTO implements Serializable {
    private Integer id;

    private String type;

    private String key;

    private String label;

    private HashMap definition;

    private String entity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private Boolean isEnabled;

}

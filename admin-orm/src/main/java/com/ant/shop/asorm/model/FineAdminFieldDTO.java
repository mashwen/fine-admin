package com.ant.shop.asorm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "字段类型不能为空！",groups = AddFieldCheck.class)
    private String type;

    @NotNull(message = "参数名称不能为空！",groups = AddFieldCheck.class)
    private String key;

    @NotNull(message = "字段类型不能为空！",groups = AddFieldCheck.class)
    private String label;

    private HashMap definition;

    private String entity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @NotNull(message = "启用/禁用状态不能为空！",groups = AddFieldCheck.class)
    private Boolean isEnabled;

    /**
     * 验证新增字段 所属组
     */
    public interface AddFieldCheck{}

}

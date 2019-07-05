package com.ant.shop.asorm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 16:50
 * @Version 1.0
 **/
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DistrictAreaDTO implements Serializable {
    private String name;

    private Integer districtId;
}

package com.ant.shop.asorm.model;

import com.ant.shop.asorm.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 添加组织需要的实体
 *
 * @Author liuzongqiang
 * @Date 2019/6/24 0024 12:11
 * @Version 1.0
 **/
@Data
public class AddOrganizationDTO implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private String remark;

    private Integer parentId;

    private Short sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private String shortName;

    private String parentShortName;

    private String address;

    private BigDecimal longtitude;

    private BigDecimal latitude;

    private Byte status;

    private Byte businessModel;

    private Boolean isEnabled;

    private Byte type;

    private FineOrgDistrict orgDistrict;

    private FineDistrict fineDistrict;

    private FineStore fineStore;

    private List<FineArea> fineAreaList;

    private List<FineAdminFieldData> fineAdminFieldDataList;
}

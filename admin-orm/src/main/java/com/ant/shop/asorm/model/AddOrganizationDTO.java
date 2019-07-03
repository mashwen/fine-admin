package com.ant.shop.asorm.model;

import com.ant.shop.asorm.entity.FineStore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class  AddOrganizationDTO {

    @NotNull(message = "组织id不能为空！",groups = RedactOrgCheck.class)
    private Integer id;

    @Size(max = 16,message = "组织编码不能超过16位！",groups = RedactOrgCheck.class)
    private String code;

    @NotNull(message = "组织名称不能为空！",groups = AddOrgCheck.class)
    private String name;

    @NotNull(message = "组织简称不能为空！",groups = AddOrgCheck.class)
    private String remark;

    @NotNull(message = "上级组织不能为空！",groups = AddOrgCheck.class)
    private Integer parentId;

    @NotNull(message = "排序号不能为空！",groups = AddOrgCheck.class)
    private Short sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @Size(max = 8,message = "组织简称，不能超过8个中文！",groups = {RedactOrgCheck.class,AddOrgCheck.class})
    private String shortName;

    private String parentShortName;

    private String address;

    private BigDecimal longtitude;

    private BigDecimal latitude;

    private Byte status;

    private Byte businessModel;

    @NotNull(message = "启用状态不能为空！",groups = AddOrgCheck.class)
    private Boolean isEnabled;

    @NotNull(message = "组织类型不能为空！",groups = AddOrgCheck.class)
    private Byte type;

    //员工人数
    private Integer staffCount;

    @NotNull(message = "行政区域id不能为空！",groups = AddOrganizationDTO.AddOrgCheck.class)
    private Integer districtId;

    @Valid
    private FineStore fineStore;

    @Valid
    @NotNull(message = "对象fineAdminFieldDataList不能为空！",groups = AddOrgCheck.class)
    private List<FineAdminFields> fineAdminFieldDataList;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class  FineAdminFields{
        private Integer id;

        private Integer refId;

        @NotNull(message = "自定义字段值id不能为空！",groups = AddOrganizationDTO.AddOrgCheck.class)
        private Integer fieldId;

        @NotNull(message = "自定义字段value不能为空！",groups = AddOrganizationDTO.AddOrgCheck.class)
        private String value;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date created;
    }

    /**
     * 验证新增组织 所属组
     */
    public interface AddOrgCheck{}

    /**
     * 验证编辑组织 所属组
     */
    public interface RedactOrgCheck{}




}

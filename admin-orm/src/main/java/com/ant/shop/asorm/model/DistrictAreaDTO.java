package com.ant.shop.asorm.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 16:50
 * @Version 1.0
 **/
@Data
public class DistrictAreaDTO implements Serializable {

    @NotNull(message = "业务区域名称不能为空！",groups =AddDistrictAreaCheck.class)
    private String name;
    @NotNull(message = "行政区域id不能为空！",groups =AddDistrictAreaCheck.class)
    private List<Integer> districtId;

    public interface AddDistrictAreaCheck{}
}

package com.ant.shop.asorm.model;

import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.entity.FineDistrictAreaKey;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 16:50
 * @Version 1.0
 **/
@Data
public class DistrictAreaDTO implements Serializable {
    private FineArea fineArea;
    private FineDistrictAreaKey fineDistrictAreaKey;
}

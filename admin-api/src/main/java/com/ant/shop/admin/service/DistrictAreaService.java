package com.ant.shop.admin.service;

import com.ant.shop.asorm.model.DistrictAreaDTO;
import response.ResultModel;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 17:18
 * @Version 1.0
 **/
public interface DistrictAreaService {
    /**
     * 新增业务区域
     * @param districtAreaDTO
     * @return
     */
    public ResultModel addArea(DistrictAreaDTO districtAreaDTO);

    /**
     * 删除业务区域
     * @param id
     * @return
     */
    public ResultModel deleteArea(Integer id);
}

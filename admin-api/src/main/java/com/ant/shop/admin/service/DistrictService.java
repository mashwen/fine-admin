package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageListResp;
import response.ResultModel;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 9:41
 * @Version 1.0
 **/
public interface DistrictService {
    /**
     * 获取行政区域列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageListResp<OrganizationDTO> getDistrictList(Integer pageNum, Integer pageSize);

    /**
     * 删除行政区域
     * @param districtId
     */
    public ResultModel delDistrictById(Integer districtId);

    /**
     * 修改行政区域
     * @param fineDistrict
     */
    public ResultModel updateDistrict(FineDistrict fineDistrict);

    /**
     * 新增行政区域
     * @param fineDistrict
     */
    public ResultModel addDistrict(FineDistrict fineDistrict);
}

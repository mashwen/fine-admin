package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageListResp;
import response.ResultModel;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 10:42
 * @Version 1.0
 **/
public interface AreaService {
    /**
     * 获取业务区域列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageListResp<OrganizationDTO> getAreaList(Integer pageNum, Integer pageSize);

    /**
     * 删除业务区域
     * @param id
     */
    public ResultModel delAreaById(Integer id);

    /**
     * 修改业务区域
     * @param fineArea
     */
    public ResultModel updateArea(FineArea fineArea);

    /**
     * 新增业务区域
     * @param fineArea
     */
    public ResultModel addArea(FineArea fineArea);
}

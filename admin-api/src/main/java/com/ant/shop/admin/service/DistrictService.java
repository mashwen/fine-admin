package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.model.FineDistrictDto;
import response.ResultModel;

import java.util.List;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 9:41
 * @Version 1.0
 **/
public interface DistrictService {

    /**
     * 新增行政区域
     * @param fineDistrict
     * @return
     */
    public ResultModel addDistrict(FineDistrict fineDistrict);

    /**
     * 根据id删除行政区域
     * @param id
     * @return
     */
    public ResultModel deleteDistrict(Integer id);

    /**
     * 修改行政区域
     * @param fineDistrict
     * @return
     */
    public ResultModel updateDistrict(FineDistrict fineDistrict);


    /**
     * 获取行政区域列表
     * @param parentId
     * @return
     */
    public List<FineDistrict> getDistrictList(Integer parentId);

    /**
     * 获取行政区域树状图列表
     * @param parentId
     * @return
     */
    public List<FineDistrictDto> getDistrictTree(Integer parentId);


}

package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.model.FineAdminFieldDTO;
import com.ant.shop.asorm.model.PageListResp;
import response.ResultModel;

/**
 * 字段
 *
 * @Author liuzongqiang
 * @Date 2019/6/22 0022 21:39
 * @Version 1.0
 **/
public interface FieldService {
    /**
     * 筛选字段
     * @param pageNum
     * @param pageSize
     * @param label
     * @return
     */
    public PageListResp<FineAdminField> getField(Integer pageNum, Integer pageSize,String label);

    /**
     * 字段详情
     * @param id
     * @return
     */
    public ResultModel getFieldById(Integer id);

    /**
     * 删除字段
     * @param id
     */
    public ResultModel deleteFieldById(Integer id);

    /**
     * 启用/禁用 字段
     * @param id
     * @param enabled
     */
    public ResultModel setFieldEnabled(Integer id,Boolean enabled);

    /**
     * 添加字段
     * @param fineAdminFieldDTO
     */
    public ResultModel setField(FineAdminFieldDTO fineAdminFieldDTO);

    /**
     * 编辑字段
     * @param fineAdminField
     * @return
     */
    public ResultModel updateField(FineAdminField fineAdminField);

    /**
     * 根据字段实体查询相关的基础数据
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    public ResultModel getFieldByEntity(Integer pageNum,Integer pageSize,String entity);
}

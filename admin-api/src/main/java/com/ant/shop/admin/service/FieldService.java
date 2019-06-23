package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.model.PageListResp;

/**
 * 字段
 *
 * @Author liuzongqiang
 * @Date 2019/6/22 0022 21:39
 * @Version 1.0
 **/
public interface FieldService {
    /**
     * 获取字段列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageListResp<FineAdminField> getField(Integer pageNum, Integer pageSize);

    /**
     * 删除字段
     * @param id
     */
    public void deleteFieldById(Integer id);

    /**
     * 启用/禁用 字段
     * @param id
     * @param enabled
     */
    public void setFieldEnabled(Integer id,Boolean enabled);
}

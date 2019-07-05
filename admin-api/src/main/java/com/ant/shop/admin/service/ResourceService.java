package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.entity.FineResourceGroup;
import com.ant.shop.asorm.model.ResourceModel;
import response.ResultModel;

public interface ResourceService {
    /**
     * 添加资源
     * @param resourceModel
     * @return
     */
    ResultModel resource(ResourceModel resourceModel, Integer userId);

    /**
     * 删除资源
     * @param id
     * @return
     */
    ResultModel resourceDelete(Integer id, Integer userId);

    /**
     * 查看资源列表
     * @param id
     * @return
     */
    ResultModel resourceList(int id);

    /**
     * 添加资源组
     * @return
     */
    ResultModel resourceGroup(FineResourceGroup fineResourceGroup, Integer userId);

    /**
     * 查询所有资源
     * @return
     */
    ResultModel selectAllResource();

    /**
     * 加载所有资源到redis
     */
    void loadResourceDefine();
}

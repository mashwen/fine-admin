package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.model.ResourceModel;
import response.ResultModel;

public interface ResourceService {
    /**
     * 添加资源
     * @param resourceModel
     * @return
     */
    ResultModel resource(ResourceModel resourceModel);

    /**
     * 删除资源
     * @param id
     * @return
     */
    ResultModel resourceDelete(int id);

    /**
     * 查看资源列表
     * @param id
     * @return
     */
    ResultModel resourceList(int id);
}

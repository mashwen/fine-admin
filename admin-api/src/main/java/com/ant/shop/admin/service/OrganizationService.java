package com.ant.shop.admin.service;

import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageListResp;

/**
 * 组织
 * @Author liuzongqiang
 * @Date 2019/6/22 0022 19:33
 * @Version 1.0
 **/
public interface OrganizationService {
    /**
     * 获取组织列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageListResp<OrganizationDTO> getOrganization(Integer pageNum, Integer pageSize);
}

package com.ant.shop.admin.service;

import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageListResp;

import java.util.List;

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

    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     */
    public void setOrganizationEnabled(Integer id,Boolean enabled);

    /**
     * 删除组织
     * @param id
     */
    public void deleteOrganizationById(Integer id);

    /**
     * 筛选组织
     * @param type
     * @param enabled
     * @param keyword
     * @return
     */
    public List<OrganizationDTO> getOrganizationByKeyword(Byte type,Boolean enabled,String keyword);

    /**
     * 新增组织
     * @param organization
     */
    public void setOrganization(OrganizationDTO organization);

}

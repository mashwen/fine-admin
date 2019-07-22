package com.ant.shop.admin.service;

import com.ant.shop.asorm.model.AddOrganizationDTO;
import com.ant.shop.asorm.model.OrganizationDTO;
import response.ResultModel;

import java.util.List;
import java.util.Map;

/**
 * 组织
 * @Author liuzongqiang
 * @Date 2019/6/22 0022 19:33
 * @Version 1.0
 **/
public interface OrganizationService {

    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     */
    public ResultModel setOrganizationEnabled(Integer id,Boolean enabled);

    /**
     * 删除组织
     * @param id
     * @return
     */
    public ResultModel deleteOrganizationById(Integer id);

    /**
     * 筛选组织
     * @param type
     * @param enabled
     * @param keyword
     * @return
     */
    public ResultModel getOrganizationByKeyword(Byte type,Boolean enabled,String keyword,Integer pageNum, Integer pageSize);

    /**
     * 组织列表
     * @param parentId
     * @return
     */
    public List<OrganizationDTO> organizationList(Integer parentId);

    /**
     * 新增组织
     * @param addOrganizationDTO
     */
    public ResultModel setOrganization(AddOrganizationDTO addOrganizationDTO);

    /**
     * 组织详情
     * @param id
     * @return
     */
    public ResultModel getOrganizationById(Integer id);

    /**
     * 编辑组织
     * @param addOrganizationDTO
     * @return
     */
    public ResultModel updateOrganization(AddOrganizationDTO addOrganizationDTO);

    /**
     * 获取员工所属的组织信息
     * @param id
     * @return
     */
    public Map getOrganizationByStaffId(Integer id);
}

package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineRole;
import com.ant.shop.asorm.model.RoleResourceModel;
import response.ResultModel;

public interface RoleService {
    /**
     * 添加角色
     * @param fineRole
     * @return
     */
    ResultModel addRole(FineRole fineRole);

    /**
     * 查询角色列表
     * @param name
     * @return
     */
    ResultModel roleList(String name, int page, int pageSize);

    /**
     * 删除角色
     * @param id
     * @return
     */
    ResultModel roleDelete(int id);

    /**
     * 编辑角色
     * @param roleResourceModels
     * @return
     */
    ResultModel roleEdit(RoleResourceModel[] roleResourceModels);

    /**
     * 查询员工角色
     * @param orgId
     * @param staffId
     * @return
     */
    ResultModel staffRole(int orgId, int staffId);
}

package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineRole;
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
}

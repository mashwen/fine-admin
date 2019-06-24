package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.RoleService;
import com.ant.shop.admin.utils.PageInfo;
import com.ant.shop.asorm.entity.FineRole;
import com.ant.shop.asorm.mapper.FineRoleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private FineRoleMapper fineRoleMapper;
    @Override
    @Transactional
    public ResultModel addRole(FineRole fineRole) {
        String uuid = null;
        uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        uuid = uuid.replaceAll("[a-zA-Z]", "");
        uuid = uuid.substring(0,9);
        Integer id = Integer.valueOf(uuid);
        System.out.println(id);
        fineRole.setId(id);
        fineRole.setCreated(new Date());
        int i = fineRoleMapper.insertSelective(fineRole);
        if (i >0){
            return ResultModel.ok();
        }
        return ResultModel.error("添加失败");
    }

    @Override
    public ResultModel roleList(String name, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<FineRole> fineRoles = fineRoleMapper.selectRoleList(name);
        if (fineRoles == null || fineRoles.size() == 0){
            return ResultModel.error("暂无角色");
        }
        int count = fineRoles.size();
        int totalPage = 0;
        if (count % pageSize == 0){
            totalPage = count / pageSize;
        }else {
            totalPage = count / pageSize + 1;
        }
        int nextPage = page + 1;
        if (page == totalPage){
            nextPage = 0;
        }
        PageInfo pageInfo = new PageInfo(page, page - 1, nextPage, count, pageSize, totalPage);
        Map<String, Object> map = new HashMap<>();
        map.put("rolesList", fineRoles);
        map.put("pageInfo", pageInfo);
        return ResultModel.ok(map);
    }
}

package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.RoleService;
import com.ant.shop.asorm.entity.FineRole;
import com.ant.shop.asorm.model.RoleResourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

import java.security.Principal;

@RestController
@RequestMapping("")
public class RoleController {
    @Autowired
    private RoleService roleService;

    //添加角色
    @PostMapping("role")
    public ResultModel addRole(@RequestBody FineRole fineRole){
        return roleService.addRole(fineRole);
    }
    //角色列表
    @GetMapping("roleList")
    public ResultModel roleList(@RequestParam(required = false) String name,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "20") int pageSize){
        return roleService.roleList(name, page, pageSize);
    }
    //删除角色
    @GetMapping("roleDelete")
    public ResultModel roleDelete(@RequestParam("id") int id){
        return roleService.roleDelete(id);
    }
    //编辑角色
    @PostMapping("roleEdit")
    public ResultModel roleEdit(@RequestBody RoleResourceModel[] roleResourceModels){
        return roleService.roleEdit(roleResourceModels);
    }
    @GetMapping("staffRole")
    public ResultModel staffRole(@RequestParam("orgId") int orgId, Principal member){
        //Integer userId = Integer.valueOf(member.getName());
        int userId = 107;
        return roleService.staffRole(orgId, userId);
    }
}

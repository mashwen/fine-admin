package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.RoleService;
import com.ant.shop.asorm.entity.FineRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

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
                                @RequestParam(required = false, defaultValue = "10") int pageSize){
        return roleService.roleList(name, page, pageSize);
    }
}

package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.FieldService;
import com.ant.shop.admin.service.OrganizationService;
import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageListResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuzongqiang
 * @Date 2019/6/22 0022 19:47
 * @Version 1.0
 **/
@RestController
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private FieldService fieldService;
    /**
     * 获取组织列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/organization")
    public ResultModel getOrganization(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        PageListResp<OrganizationDTO> orgList = organizationService.getOrganization(pageNum, pageSize);

        Map<String,Object> data=new HashMap<>(16);
        data.put("orgList",orgList);
        return  ResultModel.ok(data);
    }

    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/organization/{id}")
    public ResultModel enabledOrganization(@PathVariable(value = "id")Integer id,@RequestParam(value = "enabled")Boolean enabled){

        organizationService.setOrganizationEnabled(id,enabled);
        return ResultModel.ok();
    }

    /**
     * 删除组织
     * @param id
     * @return
     */
    @DeleteMapping("/organization/{id}")
    public ResultModel deleteOrganization(@PathVariable(value = "id")Integer id){
        if(id==null){
            return ResultModel.error("0","组织id不能为空！");
        }
        fieldService.deleteFieldById(id);
        return  ResultModel.ok();
    }

    /**
     * 获取字段列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/field")
    public ResultModel getField(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        PageListResp<FineAdminField> fieldList = fieldService.getField(pageNum, pageSize);

        Map<String,Object> data=new HashMap<>(16);
        data.put("fieldList",fieldList);
        return  ResultModel.ok(data);
    }

    /**
     * 删除字段
     * @param id
     * @return
     */
    @DeleteMapping("/field/{id}")
    public ResultModel deleteField(@PathVariable(value = "id")Integer id){
        if(id==null){
            return ResultModel.error("0","字段id不能为空！");
        }
        fieldService.deleteFieldById(id);
        return  ResultModel.ok();
    }

    /**
     * 启用/禁用 字段
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/field/{id}")
    public ResultModel enabledField(@PathVariable(value = "id")Integer id,@RequestParam(value = "enabled")Boolean enabled){

        fieldService.setFieldEnabled(id,enabled);
        return ResultModel.ok();
    }



}

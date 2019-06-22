package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.FieldService;
import com.ant.shop.admin.service.OrganizationService;
import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageListResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("organization")
    public ResultModel getOrganization(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        PageListResp<OrganizationDTO> orgList = organizationService.getOrganization(pageNum, pageSize);

        Map<String,Object> data=new HashMap<>(16);
        data.put("orgList",orgList);
        return  ResultModel.ok(data);
    }

    /**
     * 获取字段列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("field")
    public ResultModel getField(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        PageListResp<FineAdminField> fieldList = fieldService.getField(pageNum, pageSize);

        Map<String,Object> data=new HashMap<>(16);
        data.put("fieldList",fieldList);
        return  ResultModel.ok(data);
    }
}

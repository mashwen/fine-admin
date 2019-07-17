package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.ResourceService;
import com.ant.shop.asorm.entity.FineResourceGroup;
import com.ant.shop.asorm.model.ResourceModel;
import com.ant.shop.asorm.model.UserResource;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

import java.nio.Buffer;
import java.security.Principal;

@RestController
@RequestMapping("")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    //添加资源
    @PostMapping("resource")
    public ResultModel resource(@RequestBody ResourceModel resourceModel, Principal member){
        Integer userId = Integer.valueOf(member.getName());
        return resourceService.resource(resourceModel, userId);
    }
    //删除资源
    @GetMapping("resourceDelete")
    public ResultModel resourceDelete(@RequestParam("id") Integer id, Principal member){
        Integer userId = Integer.valueOf(member.getName());
        return resourceService.resourceDelete(id, userId);
    }
    //查找下级资源
    @GetMapping("resourceList")
    public ResultModel resourceList(@RequestParam(required = false, defaultValue = "0") Integer id){
        return resourceService.resourceList(id);
    }
    //添加资源组
    @PostMapping("resourceGroup")
    public ResultModel resourceGroup(@RequestBody FineResourceGroup fineResourceGroup, Principal member){
        Integer userId = Integer.valueOf(member.getName());
        return resourceService.resourceGroup(fineResourceGroup, userId);
    }
    //查找资源组和所有资源
    @GetMapping("groupAndResource")
    public ResultModel groupAndResource(){
        return resourceService.selectAllResource();
    }

    //修改资源
    @PostMapping("resourceUpDate")
    public ResultModel resourceUpDate(@RequestBody ResourceModel resourceModel,  Principal member){
        Integer userId = Integer.valueOf(member.getName());
        return resourceService.resourceUpdate(resourceModel, userId);
    }
    //根据用户判断是否有此权限
    @PostMapping("userResource")
    public ResultModel userResource(@RequestBody UserResource userResource){
        return resourceService.userResource(userResource.getUrl(), userResource.getUserId(), userResource.getOrgId());
    }

}

package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.ResourceService;
import com.ant.shop.asorm.model.ResourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

@RestController
@RequestMapping("")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    //添加资源
    @PostMapping("resource")
    public ResultModel resource(@RequestBody ResourceModel resourceModel){
        return resourceService.resource(resourceModel);
    }
    //删除资源
    @GetMapping("resourceDelete")
    public ResultModel resourceDelete(@RequestParam("id") int id){
        return resourceService.resourceDelete(id);
    }
    //查找下级资源
    @GetMapping("resourceList")
    public ResultModel resourceList(@RequestParam(required = false, defaultValue = "0") int id){
        return resourceService.resourceList(id);
    }
}

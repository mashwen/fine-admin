package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.AreaService;
import com.ant.shop.admin.service.DistrictService;
import com.ant.shop.admin.service.FieldService;
import com.ant.shop.admin.service.OrganizationService;
import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.model.AddOrganizationDTO;
import com.ant.shop.asorm.model.FineAdminFieldDTO;
import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageListResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private DistrictService districtService;
    @Autowired
    private AreaService areaService;


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
     * 新增组织
     * @param addOrganizationDTO
     * @return
     */
    @PostMapping("/organization")
    public ResultModel addOrganization(@RequestBody AddOrganizationDTO addOrganizationDTO){
        return organizationService.setOrganization(addOrganizationDTO);
    }

    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/organization/{id}")
    public ResultModel enabledOrganization(@PathVariable(value = "id")Integer id,@RequestParam(value = "enabled")Boolean enabled){
        return  organizationService.setOrganizationEnabled(id,enabled);
    }

    /**
     * 删除组织
     * @param id
     * @return
     */
    @DeleteMapping("/organization/{id}")
    public ResultModel deleteOrganization(@PathVariable(value = "id")Integer id){
        return  organizationService.deleteOrganizationById(id);
    }

    /**
     * 筛选组织
     * @param type
     * @param enabled
     * @param keyword
     * @return
     */
    @GetMapping("/organization/{type}/{enabled}")
    public ResultModel filtrateOrganization(@PathVariable("type")Byte type,@PathVariable("enabled")Boolean enabled,@RequestParam("keyword")String keyword){
        List<OrganizationDTO> orgList = organizationService.getOrganizationByKeyword(type, enabled, keyword);
        Map<String,Object> data=new HashMap<>(16);
        data.put("orgList",orgList);
        return  ResultModel.ok(data);
    }

    /**
     * 组织详情
     * @param id
     * @return
     */
    @GetMapping("/organization/{id}")
    public ResultModel OrganizationDetails(@PathVariable("id")Integer id){
        AddOrganizationDTO organizationById = organizationService.getOrganizationById(id);
        Map<String,Object> data=new HashMap<>(16);
        data.put("organization",organizationById);
        return ResultModel.ok(data);
    }

    /**
     * 行政区域列表
     * @return
     */
    @GetMapping("/district")
    public ResultModel getDistrict(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        Map<String,Object> data=new HashMap<>(16);
        PageListResp<OrganizationDTO> districtList = districtService.getDistrictList(pageNum, pageSize);
        data.put("districtList",districtList);
        return ResultModel.ok(data);
    }

    /**
     * 根据id删除行政区域
     * @param id
     * @return
     */
    @DeleteMapping("/district/{id}")
    public ResultModel delDistrict(@PathVariable("id")Integer id){
        return districtService.delDistrictById(id);
    }

    /**
     * 新增行政区域
     * @param fineDistrict
     * @return
     */
    @PostMapping("district")
    public ResultModel addDistrict(@RequestBody FineDistrict fineDistrict){
        return districtService.addDistrict(fineDistrict);
    }

    /**
     * 编辑行政区域
     * @param fineDistrict
     * @return
     */
    @PutMapping("/district")
    public ResultModel putDistrict(@RequestBody FineDistrict fineDistrict){
        return districtService.updateDistrict(fineDistrict);
    }

    /**
     * 获取业务区域列表
     * @return
     */
    @GetMapping("/area")
    public ResultModel getAreaList(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        Map<String,Object> data=new HashMap<>(16);
        PageListResp<OrganizationDTO> areaList = areaService.getAreaList(pageNum, pageSize);
        data.put("areaList",areaList);
        return ResultModel.ok(data);
    }

    /**
     * 根据id删除业务区域
     * @param id
     * @return
     */
    @DeleteMapping("/area/{id}")
    public ResultModel delAreaById(@PathVariable("id")Integer id){
        return areaService.delAreaById(id);
    }

    /**
     * 新增业务区域
     * @param fineArea
     * @return
     */
    @PostMapping("/area")
    public ResultModel addDistrict(@RequestBody FineArea fineArea){
        return areaService.addArea(fineArea);
    }

    /**
     * 编辑业务区域
     * @param fineArea
     * @return
     */
    @PutMapping("/area")
    public ResultModel putDistrict(@RequestBody FineArea fineArea){
        return  areaService.updateArea(fineArea);
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
        return  fieldService.deleteFieldById(id);
    }

    /**
     * 启用/禁用 字段
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/field/{id}")
    public ResultModel enabledField(@PathVariable(value = "id")Integer id,@RequestParam(value = "enabled")Boolean enabled){
        return fieldService.setFieldEnabled(id,enabled);
    }

    /**
     * 筛选字段
     * @param label
     * @return
     */
    @GetMapping("/fields")
    public ResultModel filtrateField(@RequestParam("label")String label){
        List<FineAdminField> fieldList = fieldService.getFieldByLabel(label);
        Map<String,Object> data=new HashMap<>(16);
        data.put("fieldList",fieldList);
        return  ResultModel.ok(data);
    }

    /**
     * 添加字段
     * @param fineAdminFieldDTO
     * @return
     */
    @PostMapping("/field")
    public ResultModel addField(@RequestBody FineAdminFieldDTO fineAdminFieldDTO){
        return fieldService.setField(fineAdminFieldDTO);
    }

}

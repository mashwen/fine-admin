package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.DistrictAreaService;
import com.ant.shop.admin.service.DistrictService;
import com.ant.shop.admin.service.FieldService;
import com.ant.shop.admin.service.OrganizationService;
import com.ant.shop.admin.utils.ControllerUtil;
import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    private DistrictAreaService districtAreaService;




    /**
     * 筛选组织
     * @param type
     * @param enabled
     * @param keyword
     * @return
     */
    @GetMapping("/organizations")
    public ResultModel filtrateOrganization(@RequestParam(value = "type",required = false)Byte type,
                                            @RequestParam(value = "enabled",required = false)Boolean enabled,
                                            @RequestParam(value = "keyword",required = false)String keyword,
                                            @RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                            @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return  organizationService.getOrganizationByKeyword(type, enabled, keyword,pageNum,pageSize);
    }

    /**
     * 组织列表
     * @return
     */
    @GetMapping("/organizationList")
    public ResultModel organizationList(){
        Map<String,Object> data=new HashMap<>(16);
        List<OrganizationDTO> orgList = organizationService.organizationList(0);
        data.put("orgList",orgList);
        return ResultModel.ok(data);
    }

    /**
     * 新增组织
     * @param addOrganizationDTO
     * @return
     */
    @PostMapping("/organization")
    public ResultModel addOrganization(@RequestBody @Validated({AddOrganizationDTO.AddOrgCheck.class}) AddOrganizationDTO addOrganizationDTO,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
           return ResultModel.error(ControllerUtil.checkParameter(bindingResult).toString());
        }
        return organizationService.setOrganization(addOrganizationDTO);
    }

    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/enabledOrg")
    public ResultModel enabledOrganization(@RequestParam( value= "id")Integer id,
                                           @RequestParam(value = "enabled")Boolean enabled){
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
     * 组织详情
     * @param id
     * @return
     */
    @GetMapping("/organization/{id}")
    public ResultModel OrganizationDetails(@PathVariable("id")Integer id){
        return organizationService.getOrganizationById(id);
    }

    /**
     * 编辑组织
     * @param addOrganizationDTO
     * @return
     */
    @PutMapping("/organization")
    public ResultModel redactOrganization(@RequestBody @Validated({AddOrganizationDTO.RedactOrgCheck.class})  AddOrganizationDTO addOrganizationDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultModel.error(ControllerUtil.checkParameter(bindingResult).toString());
        }
        return organizationService.updateOrganization(addOrganizationDTO);
    }

    /**
     * 获取行政区域列表
     * @return
     */
    @GetMapping("/district")
    public ResultModel getDistrict(@RequestParam(value = "parentId") Integer parentId){
        Map<String,Object> data=new HashMap<>(16);
        List<FineDistrict> districtList = districtService.getDistrictList(parentId);
        data.put("districtList",districtList);
        return ResultModel.ok(data);
    }

    /**
     * 获取树状行政区域列表
     * @return
     */
    @GetMapping("/districtList")
    public ResultModel getDistrictList(){
        Map<String,Object> data=new HashMap<>(16);
        List<FineDistrictDto> districtList = districtService.getDistrictTree(0);
        data.put("districtList",districtList);
        return ResultModel.ok(data);
    }

    /**
     * 新增行政区域
     * @param fineDistrict
     * @return
     */
    @PostMapping("/district")
    public ResultModel addDistrict(@RequestBody @Validated({AddOrganizationDTO.AddDistrictCheck.class}) FineDistrict fineDistrict, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultModel.error(ControllerUtil.checkParameter(bindingResult).toString());
        }
        return districtService.addDistrict(fineDistrict);
    }

    /**
     * 根据id删除行政区域
     * @param id
     * @return
     */
    @DeleteMapping("/district/{id}")
    public ResultModel delDistrict(@PathVariable("id") Integer id){
        return districtService.deleteDistrict(id);
    }

    /**
     * 编辑行政区域信息
     * @param fineDistrict
     * @return
     */
    @PutMapping("/district")
    public ResultModel redactDistrict(@RequestBody FineDistrict fineDistrict){
        return  districtService.updateDistrict(fineDistrict);
    }

    /**
     * 新增业务区域
     * @param districtAreaDTO
     * @return
     */
    @PostMapping("/districtArea")
    public ResultModel addDistrictArea(@RequestBody DistrictAreaDTO districtAreaDTO){
        return districtAreaService.addArea(districtAreaDTO);
    }

    /**
     * 根据id删除业务区域
     * @param id
     * @return
     */
    @DeleteMapping("/districtArea/{id}")
    public ResultModel delDistrictArea(@PathVariable("id")Integer id){
        return districtAreaService.deleteArea(id);
    }

    /**
     * 筛选字段
     * @param pageNum
     * @param pageSize
     * @param label
     * @return
     */
    @GetMapping("/fields")
    public ResultModel getField(@RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "label",required = false)String label){
        PageListResp<FineAdminField> fieldList = fieldService.getField(pageNum, pageSize,label);
        Map<String,Object> data=new HashMap<>(16);
        data.put("fieldList",fieldList);
        return  ResultModel.ok(data);
    }

    /**
     * 字段详情
     * @param id
     * @return
     */
    @GetMapping("/field/{id}")
    public ResultModel getFieldInfo(@PathVariable("id")Integer id){
        return fieldService.getFieldById(id);
    }

    /**
     * 删除字段
     * @param id
     * @return
     */
    @DeleteMapping("/field/{id}")
    public ResultModel deleteField(@PathVariable(value = "id")Integer id){
        if(id==null){
            return ResultModel.error("字段id不能为空！");
        }
        return  fieldService.deleteFieldById(id);
    }

    /**
     * 启用/禁用 字段
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/enabledField/{id}")
    public ResultModel enabledField(@PathVariable(value = "id")Integer id,@RequestParam(value = "enabled")Boolean enabled){
        return fieldService.setFieldEnabled(id,enabled);
    }

    /**
     * 添加字段
     * @param fineAdminFieldDTO
     * @return
     */
    @PostMapping("/field")
    public ResultModel addField(@RequestBody @Validated({FineAdminFieldDTO.AddFieldCheck.class}) FineAdminFieldDTO fineAdminFieldDTO,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultModel.error(ControllerUtil.checkParameter(bindingResult).toString());
        }
        return fieldService.setField(fineAdminFieldDTO);
    }

    /**
     * 编辑字段
     * @param fineAdminField
     * @return
     */
    @PutMapping("field")
    public ResultModel redactField(@RequestBody FineAdminField fineAdminField){
        return fieldService.updateField(fineAdminField);
    }

    /**
     * 根据字段实体查询相关的基础数据
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @GetMapping("fieldByEntity")
    public ResultModel getFieldByEntity(@RequestParam(required = false,defaultValue = "1")Integer pageNum,
                                        @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                        @RequestParam("entity") String entity){
        return fieldService.getFieldByEntity(pageNum,pageSize,entity);
    }
}

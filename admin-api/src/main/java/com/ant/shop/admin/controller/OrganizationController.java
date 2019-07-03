package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.DistrictAreaService;
import com.ant.shop.admin.service.DistrictService;
import com.ant.shop.admin.service.FieldService;
import com.ant.shop.admin.service.OrganizationService;
import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

import java.util.ArrayList;
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
     * 获取组织列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/organizations")
    public ResultModel getOrganization(@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return  organizationService.getOrganization(pageNum, pageSize);
    }

    /**
     * 新增组织
     * @param addOrganizationDTO
     * @return
     */
    @PostMapping("/organization")
    public ResultModel addOrganization(@RequestBody @Validated({AddOrganizationDTO.AddOrgCheck.class}) AddOrganizationDTO addOrganizationDTO,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> message=new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                message.add(fieldError.getDefaultMessage());
            }
            return ResultModel.error(message.toString());
        }
        return organizationService.setOrganization(addOrganizationDTO);
    }

    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/organization/enabled/{id}")
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
    @GetMapping("/organizations/filtrate")
    public ResultModel filtrateOrganization(@RequestParam("type")Byte type,@RequestParam("enabled")Boolean enabled,@RequestParam("keyword")String keyword){
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
            List<String> message=new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                message.add(fieldError.getDefaultMessage());
            }
            return ResultModel.error(message.toString());
        }
        return organizationService.updateOrganization(addOrganizationDTO);
    }

    /**
     * 根据父id查询行政区域列表
     * @return
     */
    @GetMapping("/district")
    public ResultModel getDistrict(@RequestParam("parentId")Integer parentId){
        Map<String,Object> data=new HashMap<>(16);
        List<FineDistrict> districtList = districtService.getDistrictList(parentId);
        data.put("districtList",districtList);
        return ResultModel.ok(data);
    }

    /**
     * 新增行政区域
     * @param fineDistrict
     * @return
     */
    @PostMapping("/district")
    public ResultModel addDistrict(@RequestBody FineDistrict fineDistrict){
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
    @PutMapping("/field/enabled/{id}")
    public ResultModel enabledField(@PathVariable(value = "id")Integer id,@RequestParam(value = "enabled")Boolean enabled){
        return fieldService.setFieldEnabled(id,enabled);
    }

    /**
     * 筛选字段
     * @param label
     * @return
     */
    @GetMapping("/field/filtrate")
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
     * 根据字段实体查询相关的字段
     * @param entity
     * @return
     */
    @GetMapping("field/{entity}")
    public ResultModel getFieldByEntity(@PathVariable("entity") String entity){
        return fieldService.getFieldByEntity(entity);
    }
}

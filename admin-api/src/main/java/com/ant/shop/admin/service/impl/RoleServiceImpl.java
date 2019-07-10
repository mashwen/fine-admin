package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.admin.service.RoleService;
import com.ant.shop.admin.utils.PageInfo;
import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.entity.FineRole;
import com.ant.shop.asorm.entity.FineRoleResource;
import com.ant.shop.asorm.mapper.FineResourceMapper;
import com.ant.shop.asorm.mapper.FineRoleMapper;
import com.ant.shop.asorm.mapper.FineRoleResourceMapper;
import com.ant.shop.asorm.mapper.FineStaffOrgRoleMapper;
import com.ant.shop.asorm.model.ResourceModel;
import com.ant.shop.asorm.model.RoleResourceGroupModel;
import com.ant.shop.asorm.model.RoleResourceModel;
import com.github.pagehelper.PageHelper;
import enums.LogModelEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;
import utils.JsonUtil;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private FineRoleMapper fineRoleMapper;
    @Autowired
    private FineStaffOrgRoleMapper fineStaffOrgRoleMapper;
    @Autowired
    private FineRoleResourceMapper fineRoleResourceMapper;
    @Autowired
    private FineResourceMapper fineResourceMapper;
    @Autowired
    private FineAdminLogService fineAdminLogService;
    @Override
    @Transactional
    public ResultModel addRole(FineRole fineRole, Integer userId) {
        String uuid = null;
        uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        uuid = uuid.replaceAll("[a-zA-Z]", "");
        uuid = uuid.substring(0,9);
        Integer id = Integer.valueOf(uuid);
        fineRole.setId(id);
        fineRole.setCreated(new Date());
        int i = fineRoleMapper.insertSelective(fineRole);
        if (i >0){
            FineAdminLog fineAdminLog = new FineAdminLog();
            fineAdminLog.setRefTable("fine_role");
            fineAdminLog.setRefId(id.toString());
            fineAdminLog.setContent(JsonUtil.toJson(fineRole));
            fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.CREATE_ROLE.getValue());
            fineAdminLog.setCreated(new Date());
            fineAdminLog.setCreatedBy(userId);
            fineAdminLogService.insertLog(fineAdminLog);
            return ResultModel.ok();
        }
        return ResultModel.error("添加失败");
    }

    @Override
    public ResultModel roleList(String name, int page, int pageSize) {
        if (name == ""){
            name = null;
        }
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

    @Override
    @Transactional
    public ResultModel roleDelete(int id, Integer userId) {
        FineRole fineRole = fineRoleMapper.selectByPrimaryKey(id);
        if (fineRole == null){
            return ResultModel.error("该角色不存在");
        }
        int i = fineRoleMapper.deleteByPrimaryKey(id);
        int j = fineRoleResourceMapper.deleteByPrimaryKey(id);
        int i1 = fineStaffOrgRoleMapper.deleteByRoleId(id);
        int i2 = fineRoleResourceMapper.deleteByRoleId(id);
        if (i > 0){
            FineAdminLog fineAdminLog = new FineAdminLog();
            fineAdminLog.setRefTable("fine_role");
            fineAdminLog.setRefId(id + "");
            fineAdminLog.setContent(JsonUtil.toJson(fineRole));
            fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.DELETE_ROLE.getValue());
            fineAdminLog.setCreated(new Date());
            fineAdminLog.setCreatedBy(userId);
            fineAdminLogService.insertLog(fineAdminLog);
            return ResultModel.ok();
        }
        return ResultModel.error("删除失败");
    }

    @Override
    public ResultModel roleEdit(RoleResourceModel[] roleResourceModels, Integer userId) {
        for (RoleResourceModel r : roleResourceModels) {
           int roleId = r.getRoleId();
           Map<Object, List> resource = r.getResource();
           for (Map.Entry<Object, List> resources : resource.entrySet()){
               int groupId = Integer.parseInt(resources.getKey().toString());
               if (groupId == 0){
                   List resourceId = resources.getValue();
                   for (Object o : resourceId) {
                       FineRoleResource fineRoleResource = new FineRoleResource();
                       fineRoleResource.setResourceId(Integer.parseInt(o.toString()));
                       fineRoleResource.setRoleId(roleId);
                       fineRoleResourceMapper.insertSelective(fineRoleResource);
                   }
               }else {
                   List resourceId = resources.getValue();
                   RoleResourceGroupModel roleResourceGroupModel = new RoleResourceGroupModel();
                   roleResourceGroupModel.setRoleId(roleId);
                   roleResourceGroupModel.setResourceGroupId(groupId);
                   System.out.println(roleResourceGroupModel.getRoleId());
                   System.out.println(roleResourceGroupModel.getResourceGroupId());
                   fineRoleResourceMapper.insertGroup(roleResourceGroupModel);
                   for (Object o : resourceId) {
                       FineRoleResource fineRoleResource = new FineRoleResource();
                       fineRoleResource.setResourceId(Integer.parseInt(o.toString()));
                       fineRoleResource.setRoleId(roleId);
                       fineRoleResourceMapper.insertSelective(fineRoleResource);
                   }
               }
           }
        }
        return ResultModel.ok();
    }

    @Override
    public ResultModel staffRole(int orgId, int staffId) {
        List roleList = fineStaffOrgRoleMapper.selectRole(staffId, orgId);
        Map<String, Object> map = new HashMap<>();
        List<FineRole> roles = new ArrayList<>();
        if (roleList == null || roleList.size() == 0){
            return ResultModel.error("该用户没有角色");
        }
        for (Object o : roleList) {
            FineRole role = fineRoleMapper.selectByPrimaryKey(Integer.parseInt(o.toString()));
            roles.add(role);
        }
        map.put("roleList", roles);
        return ResultModel.ok(map);
    }

//    @Override
//    public ResultModel staffRole(int orgId, int staffId) {
//        List roleList = fineStaffOrgRoleMapper.selectRole(staffId, orgId);
//        List<ResourceModel> resourcesList = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        for (Object o : roleList) {
//            List resourceList = fineRoleResourceMapper.selectResourceByRole(Integer.valueOf(o.toString()));
//            for (Object o1 : resourceList) {
//                FineResource resource = fineResourceMapper.selectResourceById(Integer.valueOf(o1.toString()));
//                ResourceModel resourceModel = new ResourceModel();
//                BeanUtils.copyProperties(resource, resourceModel);
//                if (resource.getType()){
//                    resourceModel.setType((byte)1);
//                }else {
//                    resourceModel.setType((byte)2);
//                }
//                resourcesList.add(resourceModel);
//            }
//        }
//        if (resourcesList == null || resourcesList.size() == 0){
//            return ResultModel.error("该员工没有权限");
//        }
//        map.put("resourceList", resourcesList);
//        return ResultModel.ok(map);
//    }
}

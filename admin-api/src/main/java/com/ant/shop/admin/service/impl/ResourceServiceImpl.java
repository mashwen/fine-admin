package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.admin.service.ResourceService;
import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.entity.FineResourceGroup;
import com.ant.shop.asorm.mapper.FineResourceGroupMapper;
import com.ant.shop.asorm.mapper.FineResourceMapper;
import com.ant.shop.asorm.mapper.FineRoleResourceMapper;
import com.ant.shop.asorm.model.ResourceAllGroup;
import com.ant.shop.asorm.model.ResourceModel;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import enums.LogModelEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;
import response.ResultModel;
import utils.JsonUtil;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private FineResourceMapper fineResourceMapper;
    @Autowired
    private FineRoleResourceMapper fineRoleResourceMapper;
    @Autowired
    private FineResourceGroupMapper fineResourceGroupMapper;
    @Autowired
    private FineAdminLogService fineAdminLogService;
    @Autowired
    @Lazy
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public ResultModel resource(ResourceModel resourceModel, Integer userId) {
        FineResource  fineResource = new FineResource();
        BeanUtils.copyProperties(resourceModel, fineResource);
        if (fineResource.getResourceGroupId() == null){
            fineResource.setResourceGroupId(1);
        }
        if (resourceModel.getType() == 1){
            fineResource.setType(true);
        }else {
            fineResource.setType(false);
        }
        String tempStr = resourceModel.getLabel();
        try {
            tempStr =  PinyinHelper.convertToPinyinString(resourceModel.getLabel(), "_", PinyinFormat.WITHOUT_TONE);
            } catch (Exception e)
        {
            e.printStackTrace();
        }
        fineResource.setName(tempStr);
        fineResource.setCreated(new Date());
        int i = fineResourceMapper.insertSelective(fineResource);
        if (i > 0){
            FineAdminLog fineAdminLog = new FineAdminLog();
            fineAdminLog.setRefTable("fine_resource");
            fineAdminLog.setRefId(fineResource.getResourceGroupId() + ","+ fineResource.getId());
            fineAdminLog.setContent(JsonUtil.toJson(fineResource));
            fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.CREATE_RESOURCE.getValue());
            fineAdminLog.setCreated(new Date());
            fineAdminLog.setCreatedBy(userId);
            fineAdminLogService.insertLog(fineAdminLog);
            this.loadResourceDefine();//新增资源时重新拉取资源到redis
            return ResultModel.ok();
        }
        return ResultModel.error("添加失败");
    }

    @Override
    public ResultModel resourceDelete(Integer id, Integer userId) {
        FineResource resource = fineResourceMapper.selectByPrimaryKey(id);
        if (resource == null){
            return ResultModel.error("该资源不存在");
        }
        int i = fineResourceMapper.deleteByPrimaryKey(id);
        FineAdminLog fineAdminLog = new FineAdminLog();
        fineAdminLog.setRefTable("fine_resource");
        fineAdminLog.setRefId(id.toString());
        fineAdminLog.setContent(JsonUtil.toJson(resource));
        fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.DELETE_RESOURCE.getValue());
        fineAdminLog.setCreated(new Date());
        fineAdminLog.setCreatedBy(userId);
        fineAdminLogService.insertLog(fineAdminLog);
        int i1 = fineRoleResourceMapper.deleteByResourceId(id);
        this.loadResourceDefine();//删除资源时重新拉去资源到redis
        return ResultModel.ok();
    }

    @Override
    public ResultModel resourceList(int id) {
        List<FineResource> fineResources = fineResourceMapper.selectListByParentId(id);
        if (fineResources == null || fineResources.size() == 0){
            return ResultModel.error("该资源无下级资源");
        }
        List<ResourceModel> list = new ArrayList<>();
        for (FineResource r : fineResources) {
            ResourceModel resourceModel = new ResourceModel();
            BeanUtils.copyProperties(r, resourceModel);
            if (r.getType()){
                resourceModel.setType((byte)1);
            }else {
                resourceModel.setType((byte)2);
            }
            list.add(resourceModel);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("resourceList", list);
        return ResultModel.ok(map);
    }

    @Override
    public ResultModel resourceGroup(FineResourceGroup fineResourceGroup, Integer userId) {
        fineResourceGroup.setCreated(new Date());
        int i = fineResourceGroupMapper.insertSelective(fineResourceGroup);
        if (i > 0){
            FineAdminLog fineAdminLog = new FineAdminLog();
            fineAdminLog.setRefTable("fine_resource");
            fineAdminLog.setRefId(fineResourceGroup.getId().toString());
            fineAdminLog.setContent(JsonUtil.toJson(fineResourceGroup));
            fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.CREATE_RESOURCEGROUP.getValue());
            fineAdminLog.setCreated(new Date());
            fineAdminLog.setCreatedBy(userId);
            fineAdminLogService.insertLog(fineAdminLog);
            return ResultModel.ok();
        }
        return ResultModel.error("添加失败!");
    }

    @Override
    public ResultModel selectAllResource() {
        Map<String, List> map = new HashMap<>();
        List<ResourceAllGroup> fineResourceGroups = fineResourceGroupMapper.selectAllResourceGroup();
        for (ResourceAllGroup group : fineResourceGroups) {
            List<FineResource> fineResources = fineResourceMapper.selectResourceByResourGroup(group.getId());
            group.setResource(fineResources);
        }
        map.put("ResourceAndGroup", fineResourceGroups);
        return ResultModel.ok(map);
    }

    /**
     * 加载所有资源到redis
     */
    @Override
    public void loadResourceDefine() {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        HashMap<String, Collection<ConfigAttribute>> map = new HashMap<>();

        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<FineResource> permissions = fineResourceMapper.findAll();
        for (FineResource permission : permissions) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(permission.getName());
            array.add(cfg);
            map.put(permission.getUrl(), array);
        }
        //System.out.println("map==="+map);
        ops.putAll("allResource",map);
        //System.out.println("allResource============="+ops.entries("allResource"));
    }

    @Override
    public ResultModel resourceUpdate(ResourceModel resourceModel, Integer userId) {
        String tempStr = null;
        if (resourceModel.getLabel() != null){
             tempStr = resourceModel.getLabel();
            try {
                tempStr =  PinyinHelper.convertToPinyinString(resourceModel.getLabel(), "_", PinyinFormat.WITHOUT_TONE);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        resourceModel.setName(tempStr);
        FineResource  fineResource = new FineResource();
        BeanUtils.copyProperties(resourceModel, fineResource);
        int i = fineResourceMapper.updateByPrimaryKeySelective(fineResource);
        if (i > 0){
            FineAdminLog fineAdminLog = new FineAdminLog();
            fineAdminLog.setRefTable("fine_resource");
            fineAdminLog.setRefId(resourceModel.getId().toString());
            fineAdminLog.setContent(JsonUtil.toJson(fineResource));
            fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.UPDATE_RESOURCE.getValue());
            fineAdminLog.setCreated(new Date());
            fineAdminLog.setCreatedBy(userId);
            fineAdminLogService.insertLog(fineAdminLog);
            return ResultModel.ok();
        }
        return ResultModel.error("修改失败");
    }


}

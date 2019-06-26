package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.ResourceService;
import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.mapper.FineResourceMapper;
import com.ant.shop.asorm.mapper.FineRoleResourceMapper;
import com.ant.shop.asorm.model.ResourceModel;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.ResultModel;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private FineResourceMapper fineResourceMapper;
    @Autowired
    private FineRoleResourceMapper fineRoleResourceMapper;
    @Override
    public ResultModel resource(ResourceModel resourceModel) {
        FineResource  fineResource = new FineResource();
        BeanUtils.copyProperties(resourceModel, fineResource);
        if (resourceModel.getType() == 1){
            fineResource.setType(true);
        }else {
            fineResource.setType(false);
        }
        String tempStr = null;
        try {
            tempStr =  PinyinHelper.convertToPinyinString(resourceModel.getName(), "", PinyinFormat.WITHOUT_TONE);
            } catch (Exception e)
        {
            e.printStackTrace();
        }
        fineResource.setName(tempStr);
        fineResource.setCreated(new Date());
        int i = fineResourceMapper.insertSelective(fineResource);
        if (i > 0){
            return ResultModel.ok();
        }
        return ResultModel.error("添加失败");
    }

    @Override
    public ResultModel resourceDelete(int id) {
        int i = fineResourceMapper.deleteByPrimaryKey(id);
        int i1 = fineRoleResourceMapper.deleteByResourceId(id);
        if (i > 0){
            return ResultModel.ok();
        }
        return null;
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
}

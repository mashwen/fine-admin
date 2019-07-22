package com.ant.shop.admin.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.ant.shop.admin.service.FieldService;
import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.entity.FineAdminFieldDataExample;
import com.ant.shop.asorm.entity.FineAdminFieldExample;
import com.ant.shop.asorm.mapper.FineAdminFieldDataMapper;
import com.ant.shop.asorm.mapper.FineAdminFieldMapper;
import com.ant.shop.asorm.model.FineAdminFieldDTO;
import com.ant.shop.asorm.model.PageDTO;
import com.ant.shop.asorm.model.PageListResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuzongqiang
 * @Date 2019/6/22 0022 21:42
 * @Version 1.0
 **/
@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FineAdminFieldMapper fineAdminFieldMapper;
    @Autowired
    private FineAdminFieldDataMapper fineAdminFieldDataMapper;

    /**
     * 筛选字段
     * @param pageNum
     * @param pageSize
     * @param label
     * @return
     */
    @Override
    public PageListResp<FineAdminField> getField(Integer pageNum, Integer pageSize,String label) {
        //查询条件
        FineAdminFieldExample fineAdminFieldExample=new FineAdminFieldExample();
        if(StringUtils.isEmpty(label)){
            fineAdminFieldExample.clear();
        }else{
            fineAdminFieldExample.createCriteria().andLabelEqualTo(label);
        }

        //查询全部
        List<FineAdminField> list = fineAdminFieldMapper.selectByExample(fineAdminFieldExample);
        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        //分页列表
        List<FineAdminField> fieldList = fineAdminFieldMapper.selectByExample(fineAdminFieldExample);

        PageInfo<FineAdminField> pageInfo = new PageInfo(fieldList);
        PageDTO pageDTO = new PageDTO();

        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());

        PageListResp pageList = new PageListResp();
        pageList.setList(fieldList);
        pageList.setPagination(pageDTO);
        pageList.setCount(list.size());

        return pageList;
    }

    /**
     * 字段详情
     *
     * @param id
     * @return
     */
    @Override
    public ResultModel getFieldById(Integer id) {
        if(id==null){
            return ResultModel.error("id不能为空！");
        }
        FineAdminField fineAdminField = fineAdminFieldMapper.selectByPrimaryKey(id);
        if(fineAdminField==null){
            return ResultModel.error("没有该字段");
        }
        Map<String,Object> data=new HashMap<>();
        data.put("field",fineAdminField);

        return ResultModel.ok(data);
    }

    /**
     * 删除字段
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor =Exception.class)
    public ResultModel deleteFieldById(Integer id) {
        //删除前先判断该字段是否启用状态，如果不是启用状态，则可以删除
        FineAdminField fineAdminField = fineAdminFieldMapper.selectByPrimaryKey(id);
        if(fineAdminField==null){
            return ResultModel.error("没有该字段！");
        }
        if(fineAdminField.getIsEnabled()){
            return ResultModel.error("启用状态下的字段不可以删除!");
        }
        try {
            //先删除 字段表
            fineAdminFieldMapper.deleteByPrimaryKey(id);
            //再删除字段明细表
            FineAdminFieldDataExample fineAdminFieldDataExample=new FineAdminFieldDataExample();
            fineAdminFieldDataExample.createCriteria().andFieldIdEqualTo(id);
            fineAdminFieldDataMapper.deleteByExample(fineAdminFieldDataExample);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("删除失败！");
        }
        return ResultModel.ok();
    }

    /**
     * 启用/禁用 字段
     *
     * @param id
     * @param enabled
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel setFieldEnabled(Integer id, Boolean enabled) {
        //修改的参数
        FineAdminField fineAdminField = new FineAdminField();
        fineAdminField.setIsEnabled(enabled);
        //条件
        FineAdminFieldExample fineAdminFieldExample=new FineAdminFieldExample();
        fineAdminFieldExample.createCriteria().andIdEqualTo(id);
        //执行sql
        int i = fineAdminFieldMapper.updateByExampleSelective(fineAdminField, fineAdminFieldExample);
        if(i<=0){
            return ResultModel.error("启用/禁用失败！");
        }
        return ResultModel.ok();
    }

    /**
     * 添加字段
     *
     * @param fineAdminFieldDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel setField(FineAdminFieldDTO fineAdminFieldDTO) {
        String  definition= JSONArray.toJSONString(fineAdminFieldDTO.getDefinition());

        FineAdminField fineAdminField=new FineAdminField();
        BeanUtils.copyProperties(fineAdminFieldDTO,fineAdminField);

        fineAdminField.setDefinition(definition);
        fineAdminField.setCreated(new Date());
        try {
            fineAdminFieldMapper.insert(fineAdminField);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("新增失败！");
        }

        return ResultModel.ok();
    }

    /**
     * 编辑字段
     *
     * @param fineAdminField
     * @return
     */
    @Override
    public ResultModel updateField(FineAdminField fineAdminField) {
        try {
            fineAdminFieldMapper.updateByPrimaryKeySelective(fineAdminField);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("编辑失败！");
        }
        return ResultModel.ok();
    }

    /**
     * 根据字段实体查询相关的基础数据
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @Override
    public ResultModel getFieldByEntity(Integer pageNum,Integer pageSize,String entity) {
        FineAdminFieldExample fineAdminFieldExample=new FineAdminFieldExample();
        fineAdminFieldExample.createCriteria().andEntityEqualTo(entity);
        List<FineAdminField> list = fineAdminFieldMapper.selectByExample(fineAdminFieldExample);

        //设置分页
        PageHelper.startPage(pageNum,pageSize);

        List<FineAdminField> fieldList = fineAdminFieldMapper.selectByExample(fineAdminFieldExample);

        PageInfo<FineAdminField> pageInfo = new PageInfo(fieldList);
        PageDTO pageDTO = new PageDTO();

        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());

        PageListResp pageList = new PageListResp();
        pageList.setList(fieldList);
        pageList.setPagination(pageDTO);
        pageList.setCount(list.size());

        Map<String,Object> data=new HashMap<>(16);
        data.put("fieldList",pageList);
        return  ResultModel.ok(data);
    }
}

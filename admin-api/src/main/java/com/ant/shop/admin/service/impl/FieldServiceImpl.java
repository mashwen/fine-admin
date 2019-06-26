package com.ant.shop.admin.service.impl;

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
import java.util.List;

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
     * 获取字段列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageListResp<FineAdminField> getField(Integer pageNum, Integer pageSize) {
        //查询条件
        FineAdminFieldExample fineAdminFieldExample=new FineAdminFieldExample();
        fineAdminFieldExample.clear();
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
     * 删除字段
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor =Exception.class)
    public ResultModel deleteFieldById(Integer id) {
        //删除前先判断该字段是否启用状态，如果不是启用状态，则可以删除
        FineAdminField fineAdminField = fineAdminFieldMapper.selectByPrimaryKey(id);
        if(fineAdminField.getIsEnabled()){
            return ResultModel.error("0","启用状态下的字段不可以删除!");
        }
        //先删除 字段表
        fineAdminFieldMapper.deleteByPrimaryKey(id);
        //再删除字段明细表
        FineAdminFieldDataExample fineAdminFieldDataExample=new FineAdminFieldDataExample();
        fineAdminFieldDataExample.createCriteria().andFieldIdEqualTo(id);
        fineAdminFieldDataMapper.deleteByExample(fineAdminFieldDataExample);
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
        fineAdminFieldMapper.updateByExampleSelective(fineAdminField,fineAdminFieldExample);
        return ResultModel.ok();
    }

    /**
     * 筛选字段
     *
     * @param label
     * @return
     */
    @Override
    public List<FineAdminField> getFieldByLabel(String label) {
        FineAdminFieldExample fineAdminFieldExample=new FineAdminFieldExample();
        fineAdminFieldExample.createCriteria().andLabelEqualTo(label);

        List<FineAdminField> fieldList = fineAdminFieldMapper.selectByExample(fineAdminFieldExample);
        return fieldList;
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
        fineAdminFieldMapper.insert(fineAdminField);

        return ResultModel.ok();
    }
}

package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FieldService;
import com.ant.shop.asorm.entity.*;
import com.ant.shop.asorm.mapper.FineAdminFieldDataMapper;
import com.ant.shop.asorm.mapper.FineAdminFieldMapper;
import com.ant.shop.asorm.model.PageDTO;
import com.ant.shop.asorm.model.PageListResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void deleteFieldById(Integer id) {
        //先删除 字段表
        fineAdminFieldMapper.deleteByPrimaryKey(id);
        //再删除字段明细表
        FineAdminFieldDataExample fineAdminFieldDataExample=new FineAdminFieldDataExample();
        fineAdminFieldDataExample.createCriteria().andFieldIdEqualTo(id);
        fineAdminFieldDataMapper.deleteByExample(fineAdminFieldDataExample);
    }

    /**
     * 启用/禁用 字段
     *
     * @param id
     * @param enabled
     */
    @Override
    public void setFieldEnabled(Integer id, Boolean enabled) {
        //修改的参数
        FineAdminField fineAdminField = new FineAdminField();
        fineAdminField.setIsEnabled(enabled);
        //条件
        FineAdminFieldExample fineAdminFieldExample=new FineAdminFieldExample();
        fineAdminFieldExample.createCriteria().andIdEqualTo(id);
        //执行sql
        fineAdminFieldMapper.updateByExampleSelective(fineAdminField,fineAdminFieldExample);
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
}

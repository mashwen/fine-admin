package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FieldService;
import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.entity.FineAdminFieldExample;
import com.ant.shop.asorm.entity.FineOrg;
import com.ant.shop.asorm.mapper.FineAdminFieldMapper;
import com.ant.shop.asorm.model.PageDTO;
import com.ant.shop.asorm.model.PageListResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

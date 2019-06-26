package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.AreaService;
import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.entity.FineAreaExample;
import com.ant.shop.asorm.entity.FineDistrictAreaExample;
import com.ant.shop.asorm.entity.FineOrg;
import com.ant.shop.asorm.mapper.FineAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictAreaMapper;
import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageDTO;
import com.ant.shop.asorm.model.PageListResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;

import java.util.Date;
import java.util.List;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 10:44
 * @Version 1.0
 **/
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private FineAreaMapper fineAreaMapper;
    @Autowired
    private FineDistrictAreaMapper fineDistrictAreaMapper;

    /**
     * 获取业务区域列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageListResp<OrganizationDTO> getAreaList(Integer pageNum, Integer pageSize) {
        FineAreaExample fineAreaExample=new FineAreaExample();
        fineAreaExample.clear();
        //查询全部
        List<FineArea> list = fineAreaMapper.selectByExample(fineAreaExample);
        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        //分页列表
        List<FineArea> areaList = fineAreaMapper.selectByExample(fineAreaExample);

        PageInfo<FineOrg> pageInfo = new PageInfo(areaList);
        PageDTO pageDTO = new PageDTO();

        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());

        PageListResp pageList = new PageListResp();
        pageList.setList(areaList);
        pageList.setPagination(pageDTO);
        pageList.setCount(list.size());

        return pageList;
    }

    /**
     * 删除业务区域
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel delAreaById(Integer id) {
        //删除业务区域
        fineAreaMapper.deleteByPrimaryKey(id);
        //删除行政区域和业务区域的关系
        FineDistrictAreaExample fineDistrictAreaExample=new FineDistrictAreaExample();
        fineDistrictAreaExample.createCriteria().andAreaIdEqualTo(id);
        fineDistrictAreaMapper.deleteByExample(fineDistrictAreaExample);
        return ResultModel.ok();
    }

    /**
     * 修改业务区域
     *
     * @param fineArea
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel updateArea(FineArea fineArea) {
        FineAreaExample fineAreaExample=new FineAreaExample();
        fineAreaExample.createCriteria().andIdEqualTo(fineArea.getId());
        fineAreaMapper.updateByExampleSelective(fineArea,fineAreaExample);
        return ResultModel.ok();
    }

    /**
     * 新增业务区域
     *
     * @param fineArea
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel addArea(FineArea fineArea) {
        fineArea.setCreated(new Date());
        fineAreaMapper.insert(fineArea);
        return ResultModel.ok();
    }
}

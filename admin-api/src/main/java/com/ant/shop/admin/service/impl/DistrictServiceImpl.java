package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.DistrictService;
import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.entity.FineDistrictAreaExample;
import com.ant.shop.asorm.entity.FineDistrictExample;
import com.ant.shop.asorm.entity.FineOrg;
import com.ant.shop.asorm.mapper.FineAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictMapper;
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
 * @Date 2019/6/26 0026 9:50
 * @Version 1.0
 **/
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    FineDistrictMapper fineDistrictMapper;
    @Autowired
    FineDistrictAreaMapper fineDistrictAreaMapper;


    /**
     * 获取行政区域列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageListResp<OrganizationDTO> getDistrictList(Integer pageNum, Integer pageSize) {
        FineDistrictExample fineDistrictExample=new FineDistrictExample();
        fineDistrictExample.clear();
        //查询全部
        List<FineDistrict> list = fineDistrictMapper.selectByExample(fineDistrictExample);
        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        //分页列表
        List<FineDistrict> districtList = fineDistrictMapper.selectByExample(fineDistrictExample);

        PageInfo<FineOrg> pageInfo = new PageInfo(districtList);
        PageDTO pageDTO = new PageDTO();

        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());

        PageListResp pageList = new PageListResp();
        pageList.setList(districtList);
        pageList.setPagination(pageDTO);
        pageList.setCount(list.size());

        return pageList;
    }

    /**
     * 删除行政区域
     *
     * @param districtId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel delDistrictById(Integer districtId) {
        //删除行政区域
        fineDistrictMapper.deleteByPrimaryKey(districtId);

        //删除行政区域和业务区域的关系
        FineDistrictAreaExample fineDistrictAreaExample=new FineDistrictAreaExample();
        fineDistrictAreaExample.createCriteria().andDistrictIdEqualTo(districtId);

        fineDistrictAreaMapper.deleteByExample(fineDistrictAreaExample);
        return ResultModel.ok();
    }

    /**
     * 修改行政区域
     *
     * @param fineDistrict
     */
    @Override
    public ResultModel updateDistrict(FineDistrict fineDistrict) {
        FineDistrictExample fineDistrictExample=new FineDistrictExample();
        fineDistrictExample.createCriteria().andIdEqualTo(fineDistrict.getId());
        fineDistrictMapper.updateByExampleSelective(fineDistrict,fineDistrictExample);
        return ResultModel.ok();
    }

    /**
     * 新增行政区域
     *
     * @param fineDistrict
     */
    @Override
    @Transactional
    public ResultModel addDistrict(FineDistrict fineDistrict) {
        fineDistrict.setCreated(new Date());
        fineDistrictMapper.insert(fineDistrict);
        return ResultModel.ok();
    }
}

package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.DistrictService;
import com.ant.shop.asorm.entity.*;
import com.ant.shop.asorm.mapper.FineAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictMapper;
import com.ant.shop.asorm.model.DistrictAreaDTO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
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



    /**
     * 获取行政区域列表
     * @param parentId
     * @return
     */
    @Override
    public List<FineDistrict> getDistrictList(Integer parentId) {
        FineDistrictExample fineDistrictExample=new FineDistrictExample();
        fineDistrictExample.createCriteria().andParentIdEqualTo(parentId);
        return fineDistrictMapper.selectByExample(fineDistrictExample);
    }

    /**
     * 新增行政区域
     *
     * @param fineDistrict
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel addDistrict(FineDistrict fineDistrict) {
        fineDistrict.setCreated(new Date());
        fineDistrictMapper.insert(fineDistrict);
        return ResultModel.ok();
    }

    /**
     * 根据id删除行政区域
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel deleteDistrict(Integer id) {
        fineDistrictMapper.deleteByPrimaryKey(id);
        return ResultModel.ok();
    }

    @Override
    public ResultModel updateDistrict(FineDistrict fineDistrict) {
        fineDistrictMapper.updateByPrimaryKey(fineDistrict);
        return ResultModel.ok();
    }


}

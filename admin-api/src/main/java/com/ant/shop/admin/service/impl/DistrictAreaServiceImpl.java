package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.DistrictAreaService;
import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.entity.FineDistrictAreaExample;
import com.ant.shop.asorm.entity.FineDistrictAreaKey;
import com.ant.shop.asorm.mapper.FineAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictAreaMapper;
import com.ant.shop.asorm.model.DistrictAreaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;

import java.util.Date;

/**
 * @Author liuzongqiang
 * @Date 2019/6/26 0026 17:18
 * @Version 1.0
 **/
@Service
public class DistrictAreaServiceImpl implements DistrictAreaService {
    @Autowired
    FineAreaMapper fineAreaMapper;
    @Autowired
    FineDistrictAreaMapper fineDistrictAreaMapper;

    /**
     * 新增业务区域
     *
     * @param districtAreaDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel addArea(DistrictAreaDTO districtAreaDTO) {
        FineArea fineArea=new FineArea();
        BeanUtils.copyProperties(districtAreaDTO.getFineArea(),fineArea);
        fineArea.setCreated(new Date());
        fineAreaMapper.insert(fineArea);

        FineDistrictAreaKey fineDistrictAreaKey=new FineDistrictAreaKey();
        BeanUtils.copyProperties(districtAreaDTO.getFineDistrictAreaKey(),fineDistrictAreaKey);
        fineDistrictAreaKey.setAreaId(fineArea.getId());
        fineDistrictAreaMapper.insert(fineDistrictAreaKey);
        return ResultModel.ok();
    }

    /**
     * 删除业务区域
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel deleteArea(Integer id) {
        fineAreaMapper.deleteByPrimaryKey(id);
        FineDistrictAreaExample fineDistrictAreaExample=new FineDistrictAreaExample();
        fineDistrictAreaExample.createCriteria().andAreaIdEqualTo(id);
        fineDistrictAreaMapper.deleteByExample(fineDistrictAreaExample);
        return ResultModel.ok();
    }
}

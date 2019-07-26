package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.DistrictAreaService;
import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.entity.FineDistrictAreaExample;
import com.ant.shop.asorm.entity.FineDistrictAreaKey;
import com.ant.shop.asorm.mapper.FineAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictAreaMapper;
import com.ant.shop.asorm.model.DistrictAreaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;

import java.util.Date;
import java.util.List;

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
        if(districtAreaDTO.getDistrictId().size()==0){
            return ResultModel.error("行政区域id不能为空！");
        }
        FineArea fineArea=new FineArea();
        fineArea.setName(districtAreaDTO.getName());
        fineArea.setCreated(new Date());

        int insert = fineAreaMapper.insert(fineArea);
        if(insert>0){
            FineDistrictAreaKey fineDistrictAreaKey=new FineDistrictAreaKey();
            fineDistrictAreaKey.setAreaId(fineArea.getId());
            List<Integer> districtIdList = districtAreaDTO.getDistrictId();
            for (Integer integer : districtIdList) {
                fineDistrictAreaKey.setDistrictId(integer);
                fineDistrictAreaMapper.insert(fineDistrictAreaKey);
            }

            return ResultModel.ok();
        }
        return  ResultModel.error("新增失败！");

    }

    /**
     * 删除业务区域
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel deleteArea(Integer id) {
        try {
            int i = fineAreaMapper.deleteByPrimaryKey(id);
            if(i>0){
                FineDistrictAreaExample fineDistrictAreaExample=new FineDistrictAreaExample();
                fineDistrictAreaExample.createCriteria().andAreaIdEqualTo(id);
                fineDistrictAreaMapper.deleteByExample(fineDistrictAreaExample);
                return ResultModel.ok();
            }
            return ResultModel.error("删除失败！");

        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("删除失败！");
        }

    }
}

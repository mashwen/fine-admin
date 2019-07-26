package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.DistrictService;
import com.ant.shop.asorm.entity.*;
import com.ant.shop.asorm.mapper.FineAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictAreaMapper;
import com.ant.shop.asorm.mapper.FineDistrictMapper;
import com.ant.shop.asorm.model.DistrictAreaDTO;
import com.ant.shop.asorm.model.FineDistrictDto;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;
import utils.StringUtils;

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
     * 获取行政区域树状图列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<FineDistrictDto> getDistrictTree(Integer parentId) {
        List<FineDistrictDto> districtList = fineDistrictMapper.selectByParentId(parentId);
        if(districtList.size()==0){
            return districtList;
        }
        for (FineDistrictDto dis : districtList) {
            dis.setChildList(getDistrictTree(dis.getId()));
        }
        return districtList;
    }

    /**
     * 新增行政区域
     *
     * @param fineDistrict
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel addDistrict (FineDistrict fineDistrict) {
        fineDistrict.setCreated(new Date());
        if(fineDistrict.getPhoneticName()!=null && !StringUtils.isEnglish(fineDistrict.getPhoneticName())){
            try {
                fineDistrict.setPhoneticName(PinyinHelper.convertToPinyinString(fineDistrict.getPhoneticName(),"_", PinyinFormat.WITHOUT_TONE));
            } catch (PinyinException e) {
                return ResultModel.error("0","汉字转拼音失败");
            }
        }

        int result = fineDistrictMapper.insert(fineDistrict);
        if(result==0){
            return ResultModel.error("新增失败！");
        }
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
        FineDistrict fineDistrict = fineDistrictMapper.selectByPrimaryKey(id);
        if(fineDistrict==null){
            return ResultModel.error("没有该行政区域！");
        }
        int result = fineDistrictMapper.deleteByPrimaryKey(id);
        if(result==0){
            return ResultModel.error("删除失败！");
        }
        return ResultModel.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel updateDistrict(FineDistrict fineDistrict) {
        if(fineDistrict.getId()==null){
            return ResultModel.error("行政区域id不能为空！");
        }
        int result = fineDistrictMapper.updateByPrimaryKeySelective(fineDistrict);
        if(result==0){
            return ResultModel.error("更新失败！");
        }
        return ResultModel.ok();
    }


}

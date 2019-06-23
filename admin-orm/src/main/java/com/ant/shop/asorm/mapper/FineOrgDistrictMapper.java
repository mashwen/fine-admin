package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineOrgDistrict;
import com.ant.shop.asorm.entity.FineOrgDistrictExample;
import com.ant.shop.asorm.entity.FineOrgDistrictKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FineOrgDistrictMapper {
    long countByExample(FineOrgDistrictExample example);

    int deleteByExample(FineOrgDistrictExample example);

    int deleteByPrimaryKey(FineOrgDistrictKey key);

    int insert(FineOrgDistrict record);

    int insertSelective(FineOrgDistrict record);

    List<FineOrgDistrict> selectByExample(FineOrgDistrictExample example);

    FineOrgDistrict selectByPrimaryKey(FineOrgDistrictKey key);

    int updateByExampleSelective(@Param("record") FineOrgDistrict record, @Param("example") FineOrgDistrictExample example);

    int updateByExample(@Param("record") FineOrgDistrict record, @Param("example") FineOrgDistrictExample example);

    int updateByPrimaryKeySelective(FineOrgDistrict record);

    int updateByPrimaryKey(FineOrgDistrict record);
}
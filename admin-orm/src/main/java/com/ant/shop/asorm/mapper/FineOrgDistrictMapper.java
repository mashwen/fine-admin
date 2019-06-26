package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineOrgDistrictExample;
import com.ant.shop.asorm.entity.FineOrgDistrictKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FineOrgDistrictMapper {
    long countByExample(FineOrgDistrictExample example);

    int deleteByExample(FineOrgDistrictExample example);

    int deleteByPrimaryKey(FineOrgDistrictKey key);

    int insert(FineOrgDistrictKey record);

    int insertSelective(FineOrgDistrictKey record);

    List<FineOrgDistrictKey> selectByExample(FineOrgDistrictExample example);

    int updateByExampleSelective(@Param("record") FineOrgDistrictKey record, @Param("example") FineOrgDistrictExample example);

    int updateByExample(@Param("record") FineOrgDistrictKey record, @Param("example") FineOrgDistrictExample example);
}
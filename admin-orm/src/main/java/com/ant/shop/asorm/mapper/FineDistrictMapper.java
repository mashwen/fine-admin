package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.entity.FineDistrictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FineDistrictMapper {
    long countByExample(FineDistrictExample example);

    int deleteByExample(FineDistrictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineDistrict record);

    int insertSelective(FineDistrict record);

    List<FineDistrict> selectByExample(FineDistrictExample example);

    FineDistrict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineDistrict record, @Param("example") FineDistrictExample example);

    int updateByExample(@Param("record") FineDistrict record, @Param("example") FineDistrictExample example);

    int updateByPrimaryKeySelective(FineDistrict record);

    int updateByPrimaryKey(FineDistrict record);

    List<FineDistrict> selectDistrict(@Param("parentId") Integer parentId);
}
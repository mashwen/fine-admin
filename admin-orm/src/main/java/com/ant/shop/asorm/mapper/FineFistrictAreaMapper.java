package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineDistrictAreaExample;
import com.ant.shop.asorm.entity.FineDistrictAreaKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FineFistrictAreaMapper {
    long countByExample(FineDistrictAreaExample example);

    int deleteByExample(FineDistrictAreaExample example);

    int deleteByPrimaryKey(FineDistrictAreaKey key);

    int insert(FineDistrictAreaKey record);

    int insertSelective(FineDistrictAreaKey record);

    List<FineDistrictAreaKey> selectByExample(FineDistrictAreaExample example);

    int updateByExampleSelective(@Param("record") FineDistrictAreaKey record, @Param("example") FineDistrictAreaExample example);

    int updateByExample(@Param("record") FineDistrictAreaKey record, @Param("example") FineDistrictAreaExample example);
}
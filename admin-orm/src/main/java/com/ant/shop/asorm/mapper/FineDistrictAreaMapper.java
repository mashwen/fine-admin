package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineDistrictAreaExample;
import com.ant.shop.asorm.entity.FineDistrictAreaKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineDistrictAreaMapper {
    long countByExample(FineDistrictAreaExample example);

    int deleteByExample(FineDistrictAreaExample example);

    int deleteByPrimaryKey(FineDistrictAreaKey key);

    int insert(FineDistrictAreaKey record);

    int insertSelective(FineDistrictAreaKey record);

    List<FineDistrictAreaKey> selectByExample(FineDistrictAreaExample example);

    int updateByExampleSelective(@Param("record") FineDistrictAreaKey record, @Param("example") FineDistrictAreaExample example);

    int updateByExample(@Param("record") FineDistrictAreaKey record, @Param("example") FineDistrictAreaExample example);
}
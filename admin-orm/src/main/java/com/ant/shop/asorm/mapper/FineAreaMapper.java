package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.entity.FineAreaExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FineAreaMapper {
    long countByExample(FineAreaExample example);

    int deleteByExample(FineAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineArea record);

    int insertSelective(FineArea record);

    List<FineArea> selectByExample(FineAreaExample example);

    @Select("SELECT fa.* FROM fine_area fa,fine_district_area fda WHERE fa.id=fda.area_id AND fda.district_id=#{districtId}")
    List<FineArea> selectByDistrictId(@Param("districtId") Integer districtId);

    FineArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineArea record, @Param("example") FineAreaExample example);

    int updateByExample(@Param("record") FineArea record, @Param("example") FineAreaExample example);

    int updateByPrimaryKeySelective(FineArea record);

    int updateByPrimaryKey(FineArea record);
}
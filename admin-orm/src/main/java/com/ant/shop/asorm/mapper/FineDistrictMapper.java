package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.entity.FineDistrictExample;
import com.ant.shop.asorm.model.FineDistrictDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface FineDistrictMapper {
    long countByExample(FineDistrictExample example);

    int deleteByExample(FineDistrictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineDistrict record);

    int insertSelective(FineDistrict record);

    List<FineDistrict> selectByExample(FineDistrictExample example);

    /**
     * 根据parentId查询列表
     * @param parentId
     * @return
     */
    @Results({
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "phoneticName",column = "phonetic_name")
    })
    @Select("SELECT * FROM fine_district WHERE parent_id=#{parentId}")
    List<FineDistrictDto> selectByParentId(@Param("parentId") Integer parentId);

    FineDistrict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineDistrict record, @Param("example") FineDistrictExample example);

    int updateByExample(@Param("record") FineDistrict record, @Param("example") FineDistrictExample example);

    int updateByPrimaryKeySelective(FineDistrict record);

    int updateByPrimaryKey(FineDistrict record);
}
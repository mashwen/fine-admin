package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.entity.FineResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineResourceMapper {
    long countByExample(FineResourceExample example);

    int deleteByExample(FineResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineResource record);

    int insertSelective(FineResource record);

    List<FineResource> selectByExample(FineResourceExample example);

    FineResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineResource record, @Param("example") FineResourceExample example);

    int updateByExample(@Param("record") FineResource record, @Param("example") FineResourceExample example);

    int updateByPrimaryKeySelective(FineResource record);

    int updateByPrimaryKey(FineResource record);

    FineResource selectResourceById(@Param("id") int id);
}
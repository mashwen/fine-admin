package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineArea;
import com.ant.shop.asorm.entity.FineAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineAreaMapper {
    long countByExample(FineAreaExample example);

    int deleteByExample(FineAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineArea record);

    int insertSelective(FineArea record);

    List<FineArea> selectByExample(FineAreaExample example);

    FineArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineArea record, @Param("example") FineAreaExample example);

    int updateByExample(@Param("record") FineArea record, @Param("example") FineAreaExample example);

    int updateByPrimaryKeySelective(FineArea record);

    int updateByPrimaryKey(FineArea record);
}
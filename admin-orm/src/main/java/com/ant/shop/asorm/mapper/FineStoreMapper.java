package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineStore;
import com.ant.shop.asorm.entity.FineStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineStoreMapper {
    long countByExample(FineStoreExample example);

    int deleteByExample(FineStoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineStore record);

    int insertSelective(FineStore record);

    List<FineStore> selectByExample(FineStoreExample example);

    FineStore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineStore record, @Param("example") FineStoreExample example);

    int updateByExample(@Param("record") FineStore record, @Param("example") FineStoreExample example);

    int updateByPrimaryKeySelective(FineStore record);

    int updateByPrimaryKey(FineStore record);
}
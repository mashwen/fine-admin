package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineFistrictAreaExample;
import com.ant.shop.asorm.entity.FineFistrictAreaKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineFistrictAreaMapper {
    long countByExample(FineFistrictAreaExample example);

    int deleteByExample(FineFistrictAreaExample example);

    int deleteByPrimaryKey(FineFistrictAreaKey key);

    int insert(FineFistrictAreaKey record);

    int insertSelective(FineFistrictAreaKey record);

    List<FineFistrictAreaKey> selectByExample(FineFistrictAreaExample example);

    int updateByExampleSelective(@Param("record") FineFistrictAreaKey record, @Param("example") FineFistrictAreaExample example);

    int updateByExample(@Param("record") FineFistrictAreaKey record, @Param("example") FineFistrictAreaExample example);
}
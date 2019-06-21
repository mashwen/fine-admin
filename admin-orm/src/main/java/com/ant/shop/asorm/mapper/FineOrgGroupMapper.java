package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineOrgGroup;
import com.ant.shop.asorm.entity.FineOrgGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineOrgGroupMapper {
    long countByExample(FineOrgGroupExample example);

    int deleteByExample(FineOrgGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineOrgGroup record);

    int insertSelective(FineOrgGroup record);

    List<FineOrgGroup> selectByExample(FineOrgGroupExample example);

    FineOrgGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineOrgGroup record, @Param("example") FineOrgGroupExample example);

    int updateByExample(@Param("record") FineOrgGroup record, @Param("example") FineOrgGroupExample example);

    int updateByPrimaryKeySelective(FineOrgGroup record);

    int updateByPrimaryKey(FineOrgGroup record);
}
package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineResourceGroup;
import com.ant.shop.asorm.entity.FineResourceGroupExample;
import java.util.List;

import com.ant.shop.asorm.model.ResourceAllGroup;
import org.apache.ibatis.annotations.Param;

public interface FineResourceGroupMapper {
    long countByExample(FineResourceGroupExample example);

    int deleteByExample(FineResourceGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineResourceGroup record);

    int insertSelective(FineResourceGroup record);

    List<FineResourceGroup> selectByExample(FineResourceGroupExample example);

    FineResourceGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineResourceGroup record, @Param("example") FineResourceGroupExample example);

    int updateByExample(@Param("record") FineResourceGroup record, @Param("example") FineResourceGroupExample example);

    int updateByPrimaryKeySelective(FineResourceGroup record);

    int updateByPrimaryKey(FineResourceGroup record);
    //查找所有的资源组
    List<ResourceAllGroup> selectAllResourceGroup();
}
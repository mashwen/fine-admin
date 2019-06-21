package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineRoleResource;
import com.ant.shop.asorm.entity.FineRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineRoleResourceMapper {
    long countByExample(FineRoleResourceExample example);

    int deleteByExample(FineRoleResourceExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(FineRoleResource record);

    int insertSelective(FineRoleResource record);

    List<FineRoleResource> selectByExample(FineRoleResourceExample example);

    FineRoleResource selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") FineRoleResource record, @Param("example") FineRoleResourceExample example);

    int updateByExample(@Param("record") FineRoleResource record, @Param("example") FineRoleResourceExample example);

    int updateByPrimaryKeySelective(FineRoleResource record);

    int updateByPrimaryKey(FineRoleResource record);
}
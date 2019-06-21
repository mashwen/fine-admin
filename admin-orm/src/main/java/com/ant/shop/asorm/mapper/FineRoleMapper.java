package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineRole;
import com.ant.shop.asorm.entity.FineRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineRoleMapper {
    long countByExample(FineRoleExample example);

    int deleteByExample(FineRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineRole record);

    int insertSelective(FineRole record);

    List<FineRole> selectByExample(FineRoleExample example);

    FineRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineRole record, @Param("example") FineRoleExample example);

    int updateByExample(@Param("record") FineRole record, @Param("example") FineRoleExample example);

    int updateByPrimaryKeySelective(FineRole record);

    int updateByPrimaryKey(FineRole record);
}
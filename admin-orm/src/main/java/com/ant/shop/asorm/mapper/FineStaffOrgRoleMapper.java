package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineStaffOrgRole;
import com.ant.shop.asorm.entity.FineStaffOrgRoleExample;
import com.ant.shop.asorm.entity.FineStaffOrgRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineStaffOrgRoleMapper {
    long countByExample(FineStaffOrgRoleExample example);

    int deleteByExample(FineStaffOrgRoleExample example);

    int deleteByPrimaryKey(FineStaffOrgRoleKey key);

    int insert(FineStaffOrgRole record);

    int insertSelective(FineStaffOrgRole record);

    List<FineStaffOrgRole> selectByExample(FineStaffOrgRoleExample example);

    FineStaffOrgRole selectByPrimaryKey(FineStaffOrgRoleKey key);

    int updateByExampleSelective(@Param("record") FineStaffOrgRole record, @Param("example") FineStaffOrgRoleExample example);

    int updateByExample(@Param("record") FineStaffOrgRole record, @Param("example") FineStaffOrgRoleExample example);

    int updateByPrimaryKeySelective(FineStaffOrgRole record);

    int updateByPrimaryKey(FineStaffOrgRole record);
}
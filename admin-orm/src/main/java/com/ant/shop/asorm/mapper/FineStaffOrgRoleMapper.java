package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineStaffOrgRoleExample;
import com.ant.shop.asorm.entity.FineStaffOrgRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineStaffOrgRoleMapper {
    long countByExample(FineStaffOrgRoleExample example);

    int deleteByExample(FineStaffOrgRoleExample example);

    int deleteByPrimaryKey(FineStaffOrgRoleKey key);

    int insert(FineStaffOrgRoleKey record);

    int insertSelective(FineStaffOrgRoleKey record);

    List<FineStaffOrgRoleKey> selectByExample(FineStaffOrgRoleExample example);

    int updateByExampleSelective(@Param("record") FineStaffOrgRoleKey record, @Param("example") FineStaffOrgRoleExample example);

    int updateByExample(@Param("record") FineStaffOrgRoleKey record, @Param("example") FineStaffOrgRoleExample example);
    //查询员工所属的组织id
    List selectOrg(@Param("staffId") int staffId);
    //查询员工在组织的角色
    List selectRole(@Param("staffId") int staffId, @Param("orgId") int orgId);
    //根据角色删除
    int deleteByRoleId(@Param("roleId") int roleId);
}
package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineStaffOrgRole;
import com.ant.shop.asorm.entity.FineStaffOrgRoleExample;
import com.ant.shop.asorm.entity.FineStaffOrgRoleKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FineStaffOrgRoleMapper {
    long countByExample(FineStaffOrgRoleExample example);

    int deleteByExample(FineStaffOrgRoleExample example);

    int deleteByPrimaryKey(FineStaffOrgRoleKey key);

    int insert(FineStaffOrgRole record);

    int insertSelective(FineStaffOrgRole record);

    @Select("SELECT COUNT(*) FROM fine_staff_org_role WHERE org_id=#{orgId}")
    Integer countByOrgId(@Param("orgId")Integer orgId);

    List<FineStaffOrgRoleKey> selectByExample(FineStaffOrgRoleExample example);

    FineStaffOrgRole selectByPrimaryKey(FineStaffOrgRoleKey key);


}
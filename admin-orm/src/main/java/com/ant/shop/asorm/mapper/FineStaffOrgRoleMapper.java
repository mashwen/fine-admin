package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineStaffOrgRole;
import com.ant.shop.asorm.entity.FineStaffOrgRoleExample;
import com.ant.shop.asorm.entity.FineStaffOrgRoleKey;
import com.ant.shop.asorm.model.StaffOrgRoleModel;
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

    //查询员工所属的组织id
    List selectOrg(@Param("staffId") int staffId);
    //查询员工在组织的角色
    List selectRole(@Param("staffId") int staffId, @Param("orgId") int orgId);

    //根据角色删除
    int deleteByRoleId(@Param("roleId") int roleId);
    int insertStaffRoleOrg(StaffOrgRoleModel StaffOrgRoleModel);

    //查询员工角色
    @Select("SELECT role_id FROM fine_staff_org_role WHERE staff_id=#{staffId}")
    List<String> selectRoles(@Param("staffId") int staffId);


}
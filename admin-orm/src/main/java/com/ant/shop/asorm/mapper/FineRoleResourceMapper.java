package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineRoleResource;
import com.ant.shop.asorm.entity.FineRoleResourceExample;
import java.util.List;

import com.ant.shop.asorm.model.RoleResourceGroupModel;
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

    //删除资源组
    int deleteByRoleId(@Param("roleId") int roleId);
    //添加资源组
    int insertGroup(RoleResourceGroupModel roleResourceGroupModel);

    List selectResourceByRole(@Param("roleId") int roleId);
}
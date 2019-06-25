package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.entity.FineAdminFieldExample;

import java.util.List;

import com.ant.shop.asorm.model.AddOrganizationDTO;
import org.apache.ibatis.annotations.Param;

public interface FineAdminFieldMapper {
    long countByExample(FineAdminFieldExample example);

    int deleteByExample(FineAdminFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineAdminField record);

    int insertSelective(FineAdminField record);

    List<FineAdminField> selectByExample(FineAdminFieldExample example);

    List<AddOrganizationDTO.FineAdminFields> selectByRefId(@Param("refId") Integer refId);

    FineAdminField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineAdminField record, @Param("example") FineAdminFieldExample example);

    int updateByExample(@Param("record") FineAdminField record, @Param("example") FineAdminFieldExample example);

    int updateByPrimaryKeySelective(FineAdminField record);

    int updateByPrimaryKey(FineAdminField record);
}
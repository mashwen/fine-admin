package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineAdminFieldData;
import com.ant.shop.asorm.entity.FineAdminFieldDataExample;
import com.ant.shop.asorm.model.AddOrganizationDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FineAdminFieldDataMapper {
    long countByExample(FineAdminFieldDataExample example);

    int deleteByExample(FineAdminFieldDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineAdminFieldData record);

    int insertSelective(FineAdminFieldData record);

    List<FineAdminFieldData> selectByExample(FineAdminFieldDataExample example);

    List<AddOrganizationDTO.FineAdminFields> selectByRefId(@Param("refId") Integer refId);

    FineAdminFieldData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineAdminFieldData record, @Param("example") FineAdminFieldDataExample example);

    int updateByExample(@Param("record") FineAdminFieldData record, @Param("example") FineAdminFieldDataExample example);

    int updateByPrimaryKeySelective(FineAdminFieldData record);

    int updateByPrimaryKey(FineAdminFieldData record);
}
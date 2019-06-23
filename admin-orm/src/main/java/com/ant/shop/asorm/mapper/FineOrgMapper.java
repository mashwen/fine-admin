package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineOrg;
import com.ant.shop.asorm.entity.FineOrgExample;
import com.ant.shop.asorm.model.OrganizationDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FineOrgMapper {
    long countByExample(FineOrgExample example);

    int deleteByExample(FineOrgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineOrg record);

    int insertSelective(FineOrg record);

    List<FineOrg> selectByExample(FineOrgExample example);

    @Results({
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "shortName", column = "short_name"),
            @Result(property = "parentShortName", column = "parent_short_name"),
            @Result(property = "businessModel", column = "business_model"),
            @Result(property = "isEnabled", column = "is_enabled")
    })
    @Select("SELECT a.*,b.short_name AS parent_short_name FROM fine_org a LEFT JOIN fine_org b ON(b.id=a.parent_id)")
    List<OrganizationDTO> selectAll();

    FineOrg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineOrg record, @Param("example") FineOrgExample example);

    int updateByExample(@Param("record") FineOrg record, @Param("example") FineOrgExample example);

    int updateByPrimaryKeySelective(FineOrg record);

    int updateByPrimaryKey(FineOrg record);
}
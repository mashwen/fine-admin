package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineOrg;
import com.ant.shop.asorm.entity.FineOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineOrgMapper {
    long countByExample(FineOrgExample example);

    int deleteByExample(FineOrgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineOrg record);

    int insertSelective(FineOrg record);

    List<FineOrg> selectByExample(FineOrgExample example);

    FineOrg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineOrg record, @Param("example") FineOrgExample example);

    int updateByExample(@Param("record") FineOrg record, @Param("example") FineOrgExample example);

    int updateByPrimaryKeySelective(FineOrg record);

    int updateByPrimaryKey(FineOrg record);
}
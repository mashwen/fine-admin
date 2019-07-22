package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineOrg;
import com.ant.shop.asorm.entity.FineOrgExample;
import com.ant.shop.asorm.model.OrganizationDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FineOrgMapper {
    long countByExample(FineOrgExample example);

    int deleteByExample(FineOrgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineOrg record);

    int insertSelective(FineOrg record);

    List<FineOrg> selectByExample(FineOrgExample example);

    /**
     * 根据条件筛选组织
     * @param org
     * @return
     */
    List<OrganizationDTO> selectByKeyword(@Param("org") OrganizationDTO org);

    /**
     * 根据parentId查询组织列表
     * @param parentId
     * @return
     */
    List<OrganizationDTO> selectByParentId(@Param("parentId") Integer parentId);

    /**
     * 通过员工id查询所属的组织信息
     * @param staffId
     * @return
     */
    FineOrg selectOrgByStaffId(@Param("staffId")Integer staffId);

    FineOrg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineOrg record, @Param("example") FineOrgExample example);

    int updateByExample(@Param("record") FineOrg record, @Param("example") FineOrgExample example);

    int updateByPrimaryKeySelective(FineOrg record);

    int updateByPrimaryKey(FineOrg record);

    String selectNameById(@Param("id") int id);
}
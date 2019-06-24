package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.entity.FineStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineStaffMapper {
    long countByExample(FineStaffExample example);

    int deleteByExample(FineStaffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineStaff record);

    int insertSelective(FineStaff record);

    List<FineStaff> selectByExample(FineStaffExample example);

    FineStaff selectByPrimaryKey(Integer id);

    FineStaff findByMobileAndEmail(String loginName);

    int updateByExampleSelective(@Param("record") FineStaff record, @Param("example") FineStaffExample example);

    int updateByExample(@Param("record") FineStaff record, @Param("example") FineStaffExample example);

    int updateByPrimaryKeySelective(FineStaff record);

    int updateByPrimaryKey(FineStaff record);

    List<FineStaff> selectStaffList(@Param("fullname") String fullname, @Param("mobile") String mobile, @Param("email") String email);
}
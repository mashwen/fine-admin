package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.entity.FineAdminLogExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FineAdminLogMapper {
    long countByExample(FineAdminLogExample example);

    int deleteByExample(FineAdminLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineAdminLog record);

    int insertSelective(FineAdminLog record);

    List<FineAdminLog> selectByExample(FineAdminLogExample example);

    List<FineAdminLog> selectByCreatorId(Integer id);

    List<FineAdminLog> selectAllLogs();

    List<FineAdminLog> selectByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<FineAdminLog> selectStaffLogsByDate(@Param("id")Integer id,@Param("startDate") Date startDate,@Param("endDate")Date endDate);

    FineAdminLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineAdminLog record, @Param("example") FineAdminLogExample example);

    int updateByExample(@Param("record") FineAdminLog record, @Param("example") FineAdminLogExample example);

    int updateByPrimaryKeySelective(FineAdminLog record);

    int updateByPrimaryKey(FineAdminLog record);
}
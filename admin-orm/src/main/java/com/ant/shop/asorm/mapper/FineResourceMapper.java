package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.entity.FineResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FineResourceMapper {
    long countByExample(FineResourceExample example);

    int deleteByExample(FineResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineResource record);

    int insertSelective(FineResource record);

    List<FineResource> selectByExample(FineResourceExample example);

    FineResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FineResource record, @Param("example") FineResourceExample example);

    int updateByExample(@Param("record") FineResource record, @Param("example") FineResourceExample example);

    int updateByPrimaryKeySelective(FineResource record);

    int updateByPrimaryKey(FineResource record);
    //根据id查询资源
    FineResource selectResourceById(@Param("id") int id);
    //根据id查询下级资源
    List selectListByParentId(@Param("parentId") int parentId);

    //查询所有资源
    @Select("SELECT * FROM fine_resource")
    List<FineResource> findAll();

    //查询所有资源
    List<FineResource> findAllByStaffId(@Param("staff_id") int staff_id);

}
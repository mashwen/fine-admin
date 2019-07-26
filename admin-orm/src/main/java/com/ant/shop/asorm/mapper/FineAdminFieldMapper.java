package com.ant.shop.asorm.mapper;

import com.ant.shop.asorm.entity.FineAdminField;
import com.ant.shop.asorm.entity.FineAdminFieldExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FineAdminFieldMapper {
    long countByExample(FineAdminFieldExample example);

    int deleteByExample(FineAdminFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FineAdminField record);

    int insertSelective(FineAdminField record);

    List<FineAdminField> selectByExample(FineAdminFieldExample example);

    FineAdminField selectByPrimaryKey(Integer id);

    /**
     * 检查字段是否重复
     * @param key
     * @param entity
     * @return
     */
    @Select("SELECT COUNT(*) FROM fine_admin_field WHERE `key`=#{key} AND entity=#{entity} ")
    Integer checkField(@Param("key")String key, @Param("entity")String entity);

    int updateByExampleSelective(@Param("record") FineAdminField record, @Param("example") FineAdminFieldExample example);

    int updateByExample(@Param("record") FineAdminField record, @Param("example") FineAdminFieldExample example);

    int updateByPrimaryKeySelective(FineAdminField record);

    int updateByPrimaryKey(FineAdminField record);
}
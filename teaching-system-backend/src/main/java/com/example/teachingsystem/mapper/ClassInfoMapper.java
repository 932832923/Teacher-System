
package com.example.teachingsystem.mapper;

import com.example.teachingsystem.entity.ClassInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassInfoMapper {

    @Select("SELECT * FROM class_info WHERE class_name LIKE CONCAT('%',#{keyword},'%') OR major LIKE CONCAT('%',#{keyword},'%')")
    List<ClassInfo> search(@Param("keyword") String keyword);

    @Select("SELECT * FROM class_info WHERE id = #{id}")
    ClassInfo findById(Long id);

    @Insert("INSERT INTO class_info (class_code, class_name, major, grade, create_time, update_time) VALUES (#{classCode}, #{className}, #{major}, #{grade}, NOW(), NOW())")
    void insert(ClassInfo classInfo);

    @Update("UPDATE class_info SET class_name = #{className}, major = #{major}, grade = #{grade}, update_time = NOW() WHERE id = #{id}")
    void update(ClassInfo classInfo);

    @Delete("DELETE FROM class_info WHERE id = #{id}")
    void delete(Long id);

    @Select("SELECT * FROM class_info")
    List<ClassInfo> findAll();
}

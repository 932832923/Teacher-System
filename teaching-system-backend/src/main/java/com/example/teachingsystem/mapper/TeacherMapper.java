
package com.example.teachingsystem.mapper;

import com.example.teachingsystem.entity.Teacher;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeacherMapper {

    @Select("SELECT * FROM teacher WHERE username = #{username}")
    Teacher findByUsername(String username);
}

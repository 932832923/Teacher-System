
package com.example.teachingsystem.mapper;

import com.example.teachingsystem.entity.CourseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseInfoMapper {

    @Select("SELECT * FROM course_info WHERE course_name LIKE CONCAT('%',#{keyword},'%') OR teacher_name LIKE CONCAT('%',#{keyword},'%')")
    List<CourseInfo> search(@Param("keyword") String keyword);

    @Select("SELECT * FROM course_info WHERE id = #{id}")
    CourseInfo findById(Long id);

    @Insert("INSERT INTO course_info (course_code, course_name, teacher_name, experiment_count, deadline, create_time, update_time) VALUES (#{courseCode}, #{courseName}, #{teacherName}, #{experimentCount}, #{deadline}, NOW(), NOW())")
    void insert(CourseInfo courseInfo);

    @Update("UPDATE course_info SET course_name = #{courseName}, teacher_name = #{teacherName}, experiment_count = #{experimentCount}, deadline = #{deadline}, update_time = NOW() WHERE id = #{id}")
    void update(CourseInfo courseInfo);

    @Delete("DELETE FROM course_info WHERE id = #{id}")
    void delete(Long id);

    @Select("SELECT * FROM course_info")
    List<CourseInfo> findAll();
}

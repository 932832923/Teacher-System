
package com.example.teachingsystem.mapper;

import com.example.teachingsystem.entity.AssignmentInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AssignmentInfoMapper {

    @Select("SELECT * FROM assignment_info WHERE course_id = #{courseId}")
    List<AssignmentInfo> listByCourseId(@Param("courseId") Long courseId);

    @Insert("INSERT INTO assignment_info (course_id, title, description, experiment_number, deadline, create_time, update_time) VALUES (#{courseId}, #{title}, #{description}, #{experimentNumber}, #{deadline}, NOW(), NOW())")
    void insert(AssignmentInfo assignment);

    @Update("UPDATE assignment_info SET title = #{title}, description = #{description}, experiment_number = #{experimentNumber}, deadline = #{deadline}, update_time = NOW() WHERE id = #{id}")
    void update(AssignmentInfo assignment);

    @Delete("DELETE FROM assignment_info WHERE id = #{id}")
    void delete(Long id);
}

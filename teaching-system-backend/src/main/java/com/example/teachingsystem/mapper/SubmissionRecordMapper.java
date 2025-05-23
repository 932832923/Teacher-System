
package com.example.teachingsystem.mapper;

import com.example.teachingsystem.entity.SubmissionRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubmissionRecordMapper {

    @Select("SELECT * FROM submission_record WHERE assignment_id = #{assignmentId}")
    List<SubmissionRecord> listByAssignmentId(Long assignmentId);

    @Insert("INSERT INTO submission_record (assignment_id, student_id, file_url, status, score, comment, submit_time, update_time) VALUES (#{assignmentId}, #{studentId}, #{fileUrl}, #{status}, #{score}, #{comment}, NOW(), NOW())")
    void insert(SubmissionRecord submission);

    @Update("UPDATE submission_record SET file_url = #{fileUrl}, status = #{status}, score = #{score}, comment = #{comment}, update_time = NOW() WHERE id = #{id}")
    void update(SubmissionRecord submission);

    @Delete("DELETE FROM submission_record WHERE id = #{id}")
    void delete(Long id);
}

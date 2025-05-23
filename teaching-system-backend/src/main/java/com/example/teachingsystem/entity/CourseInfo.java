
package com.example.teachingsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CourseInfo {
    private Long id;
    private String courseCode;
    private String courseName;
    private String teacherName;
    private Integer experimentCount;
    private Date deadline;
    private Date createTime;
    private Date updateTime;
}

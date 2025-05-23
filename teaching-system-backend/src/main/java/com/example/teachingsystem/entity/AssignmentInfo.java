
package com.example.teachingsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AssignmentInfo {
    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private Integer experimentNumber;
    private Date deadline;
    private Date createTime;
    private Date updateTime;
}

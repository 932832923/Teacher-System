
package com.example.teachingsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SubmissionRecord {
    private Long id;
    private Long assignmentId;
    private Long studentId;
    private String fileUrl;
    private String status;
    private Double score;
    private String comment;
    private Date submitTime;
    private Date updateTime;
}

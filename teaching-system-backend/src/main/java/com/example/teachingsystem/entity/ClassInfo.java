
package com.example.teachingsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClassInfo {
    private Long id;
    private String classCode;
    private String className;
    private String major;
    private String grade;
    private Date createTime;
    private Date updateTime;
}

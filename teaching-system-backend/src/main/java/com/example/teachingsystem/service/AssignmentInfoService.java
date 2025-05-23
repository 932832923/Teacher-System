
package com.example.teachingsystem.service;

import com.example.teachingsystem.entity.AssignmentInfo;

import java.util.List;

public interface AssignmentInfoService {
    List<AssignmentInfo> listByCourse(Long courseId);
    void add(AssignmentInfo assignment);
    void update(AssignmentInfo assignment);
    void delete(Long id);
}

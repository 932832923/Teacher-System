
package com.example.teachingsystem.service;

import com.example.teachingsystem.entity.CourseInfo;

import java.util.List;

public interface CourseInfoService {
    List<CourseInfo> search(String keyword);
    void add(CourseInfo courseInfo);
    void update(CourseInfo courseInfo);
    void delete(Long id);
    List<CourseInfo> listAll();
}


package com.example.teachingsystem.service.impl;

import com.example.teachingsystem.entity.CourseInfo;
import com.example.teachingsystem.mapper.CourseInfoMapper;
import com.example.teachingsystem.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public List<CourseInfo> search(String keyword) {
        return courseInfoMapper.search(keyword);
    }

    @Override
    public void add(CourseInfo courseInfo) {
        courseInfoMapper.insert(courseInfo);
    }

    @Override
    public void update(CourseInfo courseInfo) {
        courseInfoMapper.update(courseInfo);
    }

    @Override
    public void delete(Long id) {
        courseInfoMapper.delete(id);
    }

    @Override
    public List<CourseInfo> listAll() {
        return courseInfoMapper.findAll();
    }
}


package com.example.teachingsystem.service.impl;

import com.example.teachingsystem.entity.AssignmentInfo;
import com.example.teachingsystem.mapper.AssignmentInfoMapper;
import com.example.teachingsystem.service.AssignmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentInfoServiceImpl implements AssignmentInfoService {

    @Autowired
    private AssignmentInfoMapper assignmentMapper;

    @Override
    public List<AssignmentInfo> listByCourse(Long courseId) {
        return assignmentMapper.listByCourseId(courseId);
    }

    @Override
    public void add(AssignmentInfo assignment) {
        assignmentMapper.insert(assignment);
    }

    @Override
    public void update(AssignmentInfo assignment) {
        assignmentMapper.update(assignment);
    }

    @Override
    public void delete(Long id) {
        assignmentMapper.delete(id);
    }
}

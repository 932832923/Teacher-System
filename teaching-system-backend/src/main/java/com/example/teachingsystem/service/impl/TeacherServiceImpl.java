
package com.example.teachingsystem.service.impl;

import com.example.teachingsystem.entity.Teacher;
import com.example.teachingsystem.mapper.TeacherMapper;
import com.example.teachingsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher login(String username, String password) {
        Teacher t = teacherMapper.findByUsername(username);
        if (t != null && t.getPassword().equals(password)) {
            return t;
        }
        return null;
    }
}

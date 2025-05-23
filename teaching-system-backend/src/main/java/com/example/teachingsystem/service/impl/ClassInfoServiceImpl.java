
package com.example.teachingsystem.service.impl;

import com.example.teachingsystem.entity.ClassInfo;
import com.example.teachingsystem.mapper.ClassInfoMapper;
import com.example.teachingsystem.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> search(String keyword) {
        return classInfoMapper.search(keyword);
    }

    @Override
    public void add(ClassInfo classInfo) {
        classInfoMapper.insert(classInfo);
    }

    @Override
    public void update(ClassInfo classInfo) {
        classInfoMapper.update(classInfo);
    }

    @Override
    public void delete(Long id) {
        classInfoMapper.delete(id);
    }

    @Override
    public List<ClassInfo> listAll() {
        return classInfoMapper.findAll();
    }
}


package com.example.teachingsystem.service;

import com.example.teachingsystem.entity.ClassInfo;

import java.util.List;

public interface ClassInfoService {
    List<ClassInfo> search(String keyword);
    void add(ClassInfo classInfo);
    void update(ClassInfo classInfo);
    void delete(Long id);
    List<ClassInfo> listAll();
}


package com.example.teachingsystem.controller;

import com.example.teachingsystem.common.Result;
import com.example.teachingsystem.entity.ClassInfo;
import com.example.teachingsystem.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;

    @GetMapping("/list")
    public Result<List<ClassInfo>> list(@RequestParam(required = false, defaultValue = "") String keyword) {
        if (keyword.isEmpty()) {
            return Result.success(classInfoService.listAll());
        }
        return Result.success(classInfoService.search(keyword));
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody ClassInfo classInfo) {
        classInfoService.add(classInfo);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody ClassInfo classInfo) {
        classInfoService.update(classInfo);
        return Result.success(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        classInfoService.delete(id);
        return Result.success(null);
    }
}

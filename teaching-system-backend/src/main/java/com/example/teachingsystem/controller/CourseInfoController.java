
package com.example.teachingsystem.controller;

import com.example.teachingsystem.common.Result;
import com.example.teachingsystem.entity.CourseInfo;
import com.example.teachingsystem.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseInfoController {

    @Autowired
    private CourseInfoService courseInfoService;

    @GetMapping("/list")
    public Result<List<CourseInfo>> list(@RequestParam(required = false, defaultValue = "") String keyword) {
        if (keyword.isEmpty()) {
            return Result.success(courseInfoService.listAll());
        }
        return Result.success(courseInfoService.search(keyword));
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody CourseInfo courseInfo) {
        courseInfoService.add(courseInfo);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody CourseInfo courseInfo) {
        courseInfoService.update(courseInfo);
        return Result.success(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        courseInfoService.delete(id);
        return Result.success(null);
    }
}

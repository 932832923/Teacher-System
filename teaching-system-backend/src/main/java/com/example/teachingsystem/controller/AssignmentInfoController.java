
package com.example.teachingsystem.controller;

import com.example.teachingsystem.common.Result;
import com.example.teachingsystem.entity.AssignmentInfo;
import com.example.teachingsystem.service.AssignmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentInfoController {

    @Autowired
    private AssignmentInfoService assignmentService;

    @GetMapping("/list/{courseId}")
    public Result<List<AssignmentInfo>> list(@PathVariable Long courseId) {
        return Result.success(assignmentService.listByCourse(courseId));
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody AssignmentInfo assignment) {
        assignmentService.add(assignment);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody AssignmentInfo assignment) {
        assignmentService.update(assignment);
        return Result.success(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return Result.success(null);
    }
}

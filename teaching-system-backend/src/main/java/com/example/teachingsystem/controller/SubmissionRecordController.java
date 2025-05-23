
package com.example.teachingsystem.controller;

import com.example.teachingsystem.common.Result;
import com.example.teachingsystem.entity.SubmissionRecord;
import com.example.teachingsystem.service.SubmissionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submission")
public class SubmissionRecordController {

    @Autowired
    private SubmissionRecordService submissionService;

    @GetMapping("/list/{assignmentId}")
    public Result<List<SubmissionRecord>> list(@PathVariable Long assignmentId) {
        return Result.success(submissionService.listByAssignment(assignmentId));
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody SubmissionRecord submission) {
        submissionService.add(submission);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody SubmissionRecord submission) {
        submissionService.update(submission);
        return Result.success(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        submissionService.delete(id);
        return Result.success(null);
    }
}

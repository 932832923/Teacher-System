
package com.example.teachingsystem.controller;

import com.example.teachingsystem.common.Result;
import com.example.teachingsystem.entity.AssignmentInfo;
import com.example.teachingsystem.mapper.AssignmentInfoMapper;
import com.example.teachingsystem.mapper.SubmissionRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private AssignmentInfoMapper assignmentMapper;

    @Autowired
    private SubmissionRecordMapper submissionMapper;

    @GetMapping("/submission-rate/{courseId}")
    public Result<List<Map<String, Object>>> getSubmissionRate(@PathVariable Long courseId) {
        List<AssignmentInfo> assignments = assignmentMapper.listByCourseId(courseId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (AssignmentInfo assignment : assignments) {
            int total = 0;
            int submitted = 0;
            var submissions = submissionMapper.listByAssignmentId(assignment.getId());
            total = submissions.size();
            submitted = (int) submissions.stream().filter(s -> !"未提交".equals(s.getStatus())).count();

            Map<String, Object> entry = new HashMap<>();
            entry.put("experiment", assignment.getTitle());
            entry.put("rate", total == 0 ? 0 : (submitted * 100.0 / total));
            result.add(entry);
        }
        return Result.success(result);
    }
}

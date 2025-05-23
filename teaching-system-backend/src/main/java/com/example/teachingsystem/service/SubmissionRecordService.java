
package com.example.teachingsystem.service;

import com.example.teachingsystem.entity.SubmissionRecord;

import java.util.List;

public interface SubmissionRecordService {
    List<SubmissionRecord> listByAssignment(Long assignmentId);
    void add(SubmissionRecord submission);
    void update(SubmissionRecord submission);
    void delete(Long id);
}

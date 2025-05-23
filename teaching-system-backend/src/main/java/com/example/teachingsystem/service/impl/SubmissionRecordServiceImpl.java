
package com.example.teachingsystem.service.impl;

import com.example.teachingsystem.entity.SubmissionRecord;
import com.example.teachingsystem.mapper.SubmissionRecordMapper;
import com.example.teachingsystem.service.SubmissionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionRecordServiceImpl implements SubmissionRecordService {

    @Autowired
    private SubmissionRecordMapper submissionMapper;

    @Override
    public List<SubmissionRecord> listByAssignment(Long assignmentId) {
        return submissionMapper.listByAssignmentId(assignmentId);
    }

    @Override
    public void add(SubmissionRecord submission) {
        submissionMapper.insert(submission);
    }

    @Override
    public void update(SubmissionRecord submission) {
        submissionMapper.update(submission);
    }

    @Override
    public void delete(Long id) {
        submissionMapper.delete(id);
    }
}

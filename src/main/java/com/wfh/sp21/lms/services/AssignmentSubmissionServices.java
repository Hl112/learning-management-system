package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.AssignmentSubmission;

import java.util.List;

public interface AssignmentSubmissionServices {
    AssignmentSubmission getSubmissionById(Long assignedSubmissionId);
    AssignmentSubmission getByAssignmentIdAndUsername(Long assignmentId, String username);
    List<AssignmentSubmission> getSubmissionByAssId(Long assignedSubmissionId);
    boolean addUpdateSubmission(AssignmentSubmission assignmentSubmission);
    boolean gradeAssignmentSubmission(Long assignedSubmissionId, Float gradeScore);
}

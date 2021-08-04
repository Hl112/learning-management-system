package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.AssignmentSubmission;

public interface AssignmentSubmissionServices {
    AssignmentSubmission getSubmissionById(Long assignedSubmissionId);
    AssignmentSubmission getByAssignmentIdAndUsername(Long assignmentId, String username);
    boolean addUpdateSubmission(AssignmentSubmission assignmentSubmission);
}

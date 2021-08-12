package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
    AssignmentSubmission findByAssignmentSubmissionId(Long assignmentSubmissionId);
    AssignmentSubmission findByAssignment_AssignmentIdAndUser_Username(Long assignmentId, String username);
    List<AssignmentSubmission> findAllByAssignment_AssignmentId(Long assignmentId);
}

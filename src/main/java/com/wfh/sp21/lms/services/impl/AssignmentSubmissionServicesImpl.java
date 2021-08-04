package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.module.AssignmentSubmission;
import com.wfh.sp21.lms.repository.AssignmentSubmissionRepository;
import com.wfh.sp21.lms.services.AssignmentSubmissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AssignmentSubmissionServicesImpl implements AssignmentSubmissionServices {

    @Autowired
    private AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Override
    public AssignmentSubmission getSubmissionById(Long assignedSubmissionId) {
        return assignmentSubmissionRepository.findByAssignmentSubmissionId(assignedSubmissionId);
    }

    @Override
    public AssignmentSubmission getByAssignmentIdAndUsername(Long assignmentId, String username) {
        return assignmentSubmissionRepository.findByAssignment_AssignmentIdAndUser_Username(assignmentId,username);
    }

    @Override
    public boolean addUpdateSubmission(AssignmentSubmission assignmentSubmission) {
        AssignmentSubmission assignmentSubmissionDB;
        if(assignmentSubmission.getAssignmentSubmissionId() != null){
            assignmentSubmissionDB = assignmentSubmissionRepository.findByAssignmentSubmissionId(assignmentSubmission.getAssignmentSubmissionId());
            assignmentSubmissionDB.setTimeModified(new Date());
            assignmentSubmissionDB.setAssignment(assignmentSubmission.getAssignment());
            assignmentSubmissionDB.setUser(assignmentSubmission.getUser());
            if(assignmentSubmission.getFileName()!= null)
            assignmentSubmissionDB.setFileName(assignmentSubmission.getFileName());
            if(assignmentSubmission.getFileData() !=null)
            assignmentSubmissionDB.setFileData(assignmentSubmission.getFileData());

            assignmentSubmissionDB.setText(assignmentSubmission.getText());
            assignmentSubmissionDB.setGrade(false);
        }else assignmentSubmissionDB = assignmentSubmission;
        assignmentSubmissionRepository.save(assignmentSubmissionDB);
        return true;
    }
}

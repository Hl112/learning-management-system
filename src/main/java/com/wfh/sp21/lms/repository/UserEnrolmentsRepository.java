package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.model.UserEnrolments;
import com.wfh.sp21.lms.model.UserEnrolmentsKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEnrolmentsRepository extends JpaRepository<UserEnrolments, UserEnrolmentsKey> {
    List<UserEnrolments> findAllByCourse_CourseId(Long courseId);
    List<UserEnrolments> findAllByUser_Username(String username);
}

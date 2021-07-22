package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.User;

import java.util.List;

public interface UserEnrolmentsServices {
    List<User> getAllEnrolmentsByCourseId(Long courseId);
    List<Course> getAllEnrolmentsByUsername(String username);
    boolean addEnrolment(String username, Long courseId);
    boolean addEnrolment(Long courseId, List<String> listUsernames);
    boolean deleteEnrolment(String username, Long courseId);
    boolean deleteEnrolment(List<String> listUsernames, Long courseId);
}

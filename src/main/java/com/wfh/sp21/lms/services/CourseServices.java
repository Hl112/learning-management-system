package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.Course;

import java.util.List;

public interface CourseServices {
    List<Course> getAllCourses();
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(Course course);
}

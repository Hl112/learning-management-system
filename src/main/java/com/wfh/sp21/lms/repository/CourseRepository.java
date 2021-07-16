package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByFullName(String fullname);
    Course findByShortName(String shortname);
    Course findByCourseId(Long courseId);
}

package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByFullName(String fullname);
    Course findByShortName(String shortname);
    Course findByCourseId(Long courseId);
    List<Course> findAll();
    List<Course> findAllByUser_Username(String username);
    List<Course> findAllByActiveAndUser_Username(boolean active,String username);
}

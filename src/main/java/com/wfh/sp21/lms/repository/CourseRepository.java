package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByFullName(String fullname);
    Course findByShortName(String shortname);
    Course findByCourseId(Long courseId);
    List<Course> findAll();
    List<Course> findAllByUser_Username(String username);
    List<Course> findAllByActiveIsTrueAndVisibleIsTrueAndUser_Username(String username);
}

package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.CourseSections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSectionRepository extends JpaRepository<CourseSections, Long> {
    List<CourseSections> findAllByStatusIsTrueAndCourse_CourseId(Long courseId);
    List<CourseSections> findAllByCourse_CourseId(Long courseId);
    CourseSections findByCourseSectionId(Long courseSectionId);
}

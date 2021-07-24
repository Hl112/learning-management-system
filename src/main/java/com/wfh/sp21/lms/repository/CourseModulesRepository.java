package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.CourseModules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseModulesRepository extends JpaRepository<CourseModules, Long> {
    List<CourseModules> findAllByCourseSections_CourseSectionId(Long courseSectionId);
    List<CourseModules> findAllByVisibleIsTrueAndCourseSections_CourseSectionId(Long courseSectionId);
}

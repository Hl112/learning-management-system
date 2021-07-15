package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
    CourseCategory findByCategoryId(Long id);
    CourseCategory findByCategoryName(String categoryName);
}

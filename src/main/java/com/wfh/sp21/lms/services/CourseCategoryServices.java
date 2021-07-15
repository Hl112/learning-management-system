package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.CourseCategory;

import java.util.List;

public interface CourseCategoryServices {
    List<CourseCategory> getAllCourseCategories();
    CourseCategory getCourseCategoryByName(String courseCategoryName);
    boolean addCourseCategory(CourseCategory courseCategory);
    boolean updateCourseCategory(CourseCategory courseCategory);
    boolean deleteCourseCategory(CourseCategory courseCategory);
}

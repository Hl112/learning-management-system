package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.CourseCategory;
import com.wfh.sp21.lms.repository.CourseCategoryRepository;
import com.wfh.sp21.lms.repository.CourseRepository;
import com.wfh.sp21.lms.services.CourseSectionServices;
import com.wfh.sp21.lms.services.CourseServices;
import com.wfh.sp21.lms.services.impl.CourseSectionServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServicesImpl implements CourseServices {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private CourseSectionServices courseSectionServicesImpl;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getAllCourseByUsername(String username) {
        return courseRepository.findAllByUser_Username(username);
    }

    @Override
    public List<Course> getAllActiveCoursesByUsernameCourses(String username) {
        return courseRepository.findAllByActiveIsTrueAndVisibleIsTrueAndUser_Username(username);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public boolean addCourse(Course course) {
        Course courseDB = courseRepository.save(course);
        System.out.println("---- Begin Init Section");
        courseSectionServicesImpl.initCourseSection(courseDB);
        System.out.println("--- End Init Section");
        return true;
    }

    @Override
    public boolean updateCourse(Course course) {
        Course courseDB = courseRepository.findByCourseId(course.getCourseId());
        CourseCategory category = courseCategoryRepository.findByCategoryId(course.getCourseCategory().getCategoryId());
        courseDB.setCourseCategory(category);
        courseDB.setFullName(course.getFullName());
        courseDB.setShortName(course.getShortName());
        courseDB.setVisible(course.isVisible());
        courseDB.setStartDate(course.getStartDate());
        courseDB.setEndDate(course.getEndDate());
        courseDB.setDescription(course.getDescription());
        courseDB.setImage(course.getImage());
        courseDB.setPassword(course.getPassword());
        courseDB.setActive(course.isActive());
        courseRepository.save(courseDB);
        return true;
    }

    @Override
    public boolean deleteCourse(Course course) {
        courseRepository.deleteById(course.getCourseId());
        return true;
    }
}

package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.CourseCategory;
import com.wfh.sp21.lms.repository.CourseCategoryRepository;
import com.wfh.sp21.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServicesImpl implements CourseServices{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getAllCourseByUsername(String username) {
        return courseRepository.findAllByUser_Username(username);
    }

    @Override
    public List<Course> getAllActiveCoursesByUsernameCourses(boolean active,String username) {
        return courseRepository.findAllByActiveAndUser_Username(active,username);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public boolean addCourse(Course course) {
        courseRepository.save(course);
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

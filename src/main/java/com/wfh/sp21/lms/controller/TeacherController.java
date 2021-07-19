package com.wfh.sp21.lms.controller;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.CourseCategory;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.services.CourseCategoryServicesImpl;
import com.wfh.sp21.lms.services.CourseServicesImpl;
import com.wfh.sp21.lms.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserServicesImpl userServicesImpl;

    @Autowired
    private CourseCategoryServicesImpl courseCategoryServicesImpl;

    @Autowired
    private CourseServicesImpl courseServicesImpl;

    @GetMapping(value = {"", "/"})
    public String teacherPage() {
        return "teacher/teacher";
    }

    //Init Attriute
    @ModelAttribute("userLogin")
    User userLogin(Principal principal) {
        return userServicesImpl.getUserByUsername(principal.getName());
    }


    //Course
    @GetMapping("/course")
    public String coursePage(Model model, Principal principal) {
        model.addAttribute("module", "course");
        String username = principal.getName();
        List<Course> courseList = courseServicesImpl.getAllCourseByUsername(username);
        model.addAttribute("LIST_COURSE", courseList);
        return "teacher/course";
    }

    @GetMapping("/addCourse")
    public String addCoursePage(Model model) {
        List<CourseCategory> courseCategoryList = courseCategoryServicesImpl.getAllCourseCategories();
        model.addAttribute("module", "addCourse");
        model.addAttribute("LIST_CATEGORY", courseCategoryList);
        return "teacher/addCourse";
    }

    @GetMapping("/editCourse/{courseId}")
    public String editCoursePage(Model model, @PathVariable("courseId") Long courseId, Principal principal) {
        Course courseDB = courseServicesImpl.getCourseById(courseId);
        List<CourseCategory> courseCategoryList = courseCategoryServicesImpl.getAllCourseCategories();
        model.addAttribute("LIST_CATEGORY", courseCategoryList);
        model.addAttribute("module", "addCourse");
        String username = principal.getName();
        if (username == null) return "login";
        if (!courseDB.getUser().getUsername().equals(username))
            return "403";
        model.addAttribute("eCourse",courseDB);
        return "teacher/editCourse";

    }

    @PostMapping("/addCourse")
    @ResponseBody
    public ResponseEntity<Object> addEditCourse(@RequestBody Course course) {
        CourseCategory category = courseCategoryServicesImpl.getCourseCategoryById(course.getCourseCategory().getCategoryId());
        User user = userServicesImpl.getUserByUsername(course.getUser().getUsername());
        course.setUser(user);
        course.setCourseCategory(category);
        String success = course.getCourseId() != null ? "Sửa khóa học thành công" : "Thêm khóa học thành công";
        String fail = course.getCourseId() != null ? "Sửa khóa học thất bại" : "Thêm khóa học thất bại";
        System.out.println(course.isVisible());
        boolean result = course.getCourseId() != null ? courseServicesImpl.updateCourse(course) : courseServicesImpl.addCourse(course);
        if (result) {
            return new ResponseEntity<Object>(success, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(fail, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/course")
    @ResponseBody
    public ResponseEntity<Object> deleteCourse(@RequestBody Course course) {
        if (courseServicesImpl.deleteCourse(course)) {
            return new ResponseEntity<Object>("Xóa khóa học thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("Xóa khóa học thất bại", HttpStatus.BAD_REQUEST);
        }
    }

}

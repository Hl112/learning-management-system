package com.wfh.sp21.lms.controller;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.model.UserEnrolments;
import com.wfh.sp21.lms.services.CourseServices;
import com.wfh.sp21.lms.services.RoleServices;
import com.wfh.sp21.lms.services.UserEnrolmentsServices;
import com.wfh.sp21.lms.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private UserServices userServicesImpl;

    @Autowired
    private CourseServices courseServices;

    @Autowired
    private UserEnrolmentsServices userEnrolmentsServices;


    //Init Attriute
    @ModelAttribute("userLogin")
    User userLogin(Principal principal) {
        return userServicesImpl.getUserByUsername(principal.getName());
    }

    @GetMapping(value = {"","/"})
    public String studentPage(Model model, Principal principal){
        User user = userServicesImpl.getUserByUsername(principal.getName());
        List<Course> list_AllCourse = courseServices.getAllCourses();
        List<Course> list_EnrollmentCourse = user.getUserEnrolments() != null ? user.getUserEnrolments().stream().map(UserEnrolments::getCourse).collect(Collectors.toList()) : null;
        List<Course> list_NotEnrolled = (new ArrayList<>(list_AllCourse));
        if(list_EnrollmentCourse != null){
            for (Course course: list_EnrollmentCourse) {
                list_NotEnrolled.remove(course);
            }
        }
        model.addAttribute("allC",list_AllCourse);
        model.addAttribute("enrollC",list_EnrollmentCourse);
        model.addAttribute("nEnrollC",list_NotEnrolled);
        model.addAttribute("userLogin",user);
        return "student/student";
    }

    @GetMapping("/view")
    public String viewDetail(@RequestParam("id") Long courseId, Model model,Principal principal){
        Course course = courseServices.getCourseById(courseId);
        User user = userServicesImpl.getUserByUsername(principal.getName());
        List<Course> list_EnrollmentCourse = user.getUserEnrolments() != null ? user.getUserEnrolments().stream().map(UserEnrolments::getCourse).collect(Collectors.toList()) : null;
        boolean earlyEnrol = false;
        boolean expiredEnrol = false;
        if(course.getStartDate().getTime() - (new Date()).getTime() > 0){
            earlyEnrol = true;
        }
        if(course.getEndDate() != null){
            if(course.getEndDate().getTime() - (new Date()).getTime() < 0){
                expiredEnrol = true;
            }
        }
        model.addAttribute("earlyEnrol", earlyEnrol);
        model.addAttribute("expiredEnrol", expiredEnrol);
        model.addAttribute("course",course);
        if(list_EnrollmentCourse != null)
        if(list_EnrollmentCourse.contains(course)){
            return "student/view";
        }
        return "student/enrollCourse";
    }

    @PostMapping("/enroll")
    @ResponseBody
    public ResponseEntity<Object> enrollCourse(@RequestBody UserEnrolments userEnrolments){
        Course course = courseServices.getCourseById(userEnrolments.getCourse().getCourseId());
        User user = userServicesImpl.getUserByUsername(userEnrolments.getUser().getUsername());
        if(course.getPassword() != null){
            if(!userEnrolments.getCourse().getPassword().equals(course.getPassword())){
                return new ResponseEntity<>("Mật khẩu bạn nhập không đúng", HttpStatus.BAD_REQUEST);
            }
        }
        try {
            userEnrolmentsServices.addEnrolment(user.getUsername(), course.getCourseId());
        }catch (Exception e){
            return new ResponseEntity<>("Đăng ký thất bại",HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>("Đăng ký thành công", HttpStatus.OK);
    }
}

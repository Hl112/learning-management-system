package com.wfh.sp21.lms.controller;

import com.wfh.sp21.lms.model.CourseCategory;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.services.CourseCategoryServicesImpl;
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

    @GetMapping(value = {"","/"})
    public String teacherPage(){
        return "teacher/teacher";
    }
    //Init Attriute
    @ModelAttribute("userLogin")
    User userLogin(Principal principal) {
        return userServicesImpl.getUserByUsername(principal.getName());
    }

    //Course

    @GetMapping("/course")
    public String coursePage(Model model){
        model.addAttribute("module","course");
        return "teacher/course";
    }
}

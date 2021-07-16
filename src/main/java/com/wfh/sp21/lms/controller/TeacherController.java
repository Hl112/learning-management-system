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

    @Autowired
    private CourseCategoryServicesImpl courseCategoryServicesImpl;
    //Course Category
    @GetMapping("/courseCategory")
    public String courseCategoryPage(Model model){
        model.addAttribute("module","courseCategory");
        List<CourseCategory> courseCategoryList = courseCategoryServicesImpl.getAllCourseCategories();
        model.addAttribute("LIST_COURSE_CATEGORY", courseCategoryList);
        return "teacher/courseCategory";
    }

    @GetMapping("/addCourseCategory")
    public String addCourseCategoryPage(Model model){
        model.addAttribute("module", "addCourseCategory");
        return "teacher/addCourseCategory";
    }

    @PostMapping("/courseCategory")
    @ResponseBody
    public ResponseEntity<Object> addCourseCategory(@RequestBody CourseCategory courseCategory){
        if(courseCategoryServicesImpl.addCourseCategory(courseCategory)){
            return new ResponseEntity<Object>("Thêm danh mục khóa học thành công", HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("Thêm danh mục khóa học thất bại", HttpStatus.BAD_REQUEST);
        }
        
    }

    @PutMapping("/courseCategory")
    @ResponseBody
    public ResponseEntity<Object> updateCourseCategory(@RequestBody CourseCategory courseCategory){
        if(courseCategoryServicesImpl.updateCourseCategory(courseCategory)){
            return new ResponseEntity<Object>("Cập nhật danh mục khóa học thành công", HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("Cập nhật danh mục khóa học thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/courseCategory")
    @ResponseBody
    public ResponseEntity<Object> deleteCourseCategory(@RequestBody CourseCategory courseCategory){
        if(courseCategoryServicesImpl.deleteCourseCategory(courseCategory)){
            return new ResponseEntity<Object>("Bạn đã xóa danh mục khóa học thành công", HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("Bạn đã xóa danh mục khóa học thất bại", HttpStatus.BAD_REQUEST);
        }
    }
    //Course

    @GetMapping("/course")
    public String coursePage(Model model){
        model.addAttribute("module","course");
        return "teacher/course";
    }
}

package com.wfh.sp21.lms.controller;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.CourseModules;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.model.UserEnrolments;
import com.wfh.sp21.lms.model.module.AssignmentSubmission;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

    @Autowired
    private CourseModulesServices courseModulesServicesImpl;

    @Autowired
    private AssignmentSubmissionServices assignmentSubmissionServices;

    @Autowired
    private QuestionServices questionServices;


    //Init Attriute
    @ModelAttribute("userLogin")
    User userLogin(Principal principal) {
        return userServicesImpl.getUserByUsername(principal.getName());
    }

    @ModelAttribute("myCourses")
    List<Course> getCourseActiveByUser(Principal principal) {
        User user = userServicesImpl.getUserByUsername(principal.getName());
        return user.getUserEnrolments() != null ? user.getUserEnrolments().stream().map(UserEnrolments::getCourse).collect(Collectors.toList()) : null;
    }

    @GetMapping(value = {"", "/"})
    public String studentPage(Model model, Principal principal) {
        User user = userServicesImpl.getUserByUsername(principal.getName());
        List<Course> list_AllCourse = courseServices.getAllCourses();
        List<Course> list_EnrollmentCourse = user.getUserEnrolments() != null ? user.getUserEnrolments().stream().map(UserEnrolments::getCourse).collect(Collectors.toList()) : null;
        List<Course> list_NotEnrolled = (new ArrayList<>(list_AllCourse));
        if (list_EnrollmentCourse != null) {
            for (Course course : list_EnrollmentCourse) {
                list_NotEnrolled.remove(course);
            }
        }
        model.addAttribute("allC", list_AllCourse);
        model.addAttribute("enrollC", list_EnrollmentCourse);
        model.addAttribute("nEnrollC", list_NotEnrolled);
        model.addAttribute("userLogin", user);
        return "student/student";
    }

    @GetMapping("/view")
    public String viewDetail(@RequestParam("id") Long courseId, Model model, Principal principal) {
        Course course = courseServices.getCourseById(courseId);
        User user = userServicesImpl.getUserByUsername(principal.getName());
        List<Course> list_EnrollmentCourse = user.getUserEnrolments() != null ? user.getUserEnrolments().stream().map(UserEnrolments::getCourse).collect(Collectors.toList()) : null;
        boolean earlyEnrol = false;
        boolean expiredEnrol = false;
        if (course.getStartDate().getTime() - (new Date()).getTime() > 0) {
            earlyEnrol = true;
        }
        if (course.getEndDate() != null) {
            if (course.getEndDate().getTime() - (new Date()).getTime() < 0) {
                expiredEnrol = true;
            }
        }
        model.addAttribute("module", "" + courseId);
        model.addAttribute("earlyEnrol", earlyEnrol);
        model.addAttribute("expiredEnrol", expiredEnrol);
        model.addAttribute("course", course);
        if (list_EnrollmentCourse != null)
            if (list_EnrollmentCourse.contains(course)) {
                return "student/view";
            }
        return "student/enrollCourse";
    }

    @PostMapping("/enroll")
    @ResponseBody
    public ResponseEntity<Object> enrollCourse(@RequestBody UserEnrolments userEnrolments) {
        Course course = courseServices.getCourseById(userEnrolments.getCourse().getCourseId());
        User user = userServicesImpl.getUserByUsername(userEnrolments.getUser().getUsername());
        if (course.getPassword() != null) {
            if (!userEnrolments.getCourse().getPassword().equals(course.getPassword())) {
                return new ResponseEntity<>("Mật khẩu bạn nhập không đúng", HttpStatus.BAD_REQUEST);
            }
        }
        try {
            userEnrolmentsServices.addEnrolment(user.getUsername(), course.getCourseId());
        } catch (Exception e) {
            return new ResponseEntity<>("Đăng ký thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Đăng ký thành công", HttpStatus.OK);
    }

    @GetMapping("/viewModule")
    public String viewModule(Model model, @RequestParam("id") Long courseModuleId, HttpServletResponse response, Principal principal) {
        CourseModules courseModules = courseModulesServicesImpl.getCourseModulesByCourseModulesId(courseModuleId);
        User user = userServicesImpl.getUserByUsername(principal.getName());
        model.addAttribute("courseModule", courseModules);
        switch (courseModules.getTypeName()) {
            case "Assignment":
                AssignmentSubmission assignmentSubmission = assignmentSubmissionServices.getByAssignmentIdAndUsername(courseModuleId, user.getUsername());
                Date timeRemaining = null;
                boolean submission = true;
                if (courseModules.getAssignment().getDueDate() != null)
                    if (courseModules.getAssignment().getDueDate().getTime() - (new Date()).getTime() > 0) {
                        long diff = courseModules.getAssignment().getDueDate().getTime() - (new Date()).getTime();
                        long noDay = diff / (24 *3600 * 1000);
                        long hours = (diff % (24 *3600 * 1000)) / 3600000;
                        long minutes = ((diff % (24 *3600 * 1000)) % 3600000) / 60000;
                        model.addAttribute("dayR",noDay);
                        model.addAttribute("hourR",hours);
                        model.addAttribute("minuteR",minutes);
                        timeRemaining = new Date(diff);

                    }
                    else submission = false;
                    model.addAttribute("submission",submission);
                model.addAttribute("timeRemaining", timeRemaining);
                model.addAttribute("assignmentSubmission", assignmentSubmission);
                return "student/viewAssignment";
            case "Quiz":
                boolean earlyQuiz = false;
                boolean expiredQuiz = false;
                Quiz quiz = courseModules.getQuiz();
                if (quiz.getTimeOpen() != null)
                    if (quiz.getTimeOpen().getTime() - (new Date()).getTime() > 0) earlyQuiz = true;
                if (quiz.getTimeClose() != null)
                    if (quiz.getTimeClose().getTime() - (new Date()).getTime() < 0) expiredQuiz = true;

                model.addAttribute("earlyQuiz", earlyQuiz);
                model.addAttribute("expiredQuiz", expiredQuiz);
                return "student/viewQuiz";
            case "FileModule":
                FileModule fileModule = courseModules.getFile();
                try {
                    byte[] fileData = fileModule.getFileData();
                    // config response
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment; filename=" + fileModule.getFileName());
                    response.setContentLength(fileData.length);
                    InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(fileData));
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                } catch (Exception x) {
                    x.printStackTrace();
                }
                break;
            case "Url":
                return "redirect://" + courseModules.getUrl().getLink();
        }
        return "404";
    }

    @GetMapping("/submission")
    public String submissionAssignment(@RequestParam("id") Long assignmentId,Model model, Principal principal){
        CourseModules courseModules = courseModulesServicesImpl.getCourseModulesByCourseModulesId(assignmentId);
        AssignmentSubmission assignmentSubmission = assignmentSubmissionServices.getByAssignmentIdAndUsername(assignmentId, principal.getName());

        if(courseModules.getAssignment().getDueDate() != null){
            long timeLeft = courseModules.getAssignment().getDueDate().getTime() - (new Date()).getTime();
            model.addAttribute("timeLeft",timeLeft);
        }
        model.addAttribute("assignmentSubmission",assignmentSubmission);
        model.addAttribute("courseModule", courseModules);
        return "student/submission";
    }

    @GetMapping("/fileAssignment")
    public void downloadAssignmentFile(HttpServletResponse response, @RequestParam("id") Long assignmentId){
        CourseModules courseModules = courseModulesServicesImpl.getCourseModulesByCourseModulesId(assignmentId);
        try {
            byte[] fileData = courseModules.getAssignment().getFileData();
            // config response
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + courseModules.getAssignment().getFile());
            response.setContentLength(fileData.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(fileData));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    @PostMapping("/submission")
    @ResponseBody
    public ResponseEntity<Object> submissionAss(@RequestBody AssignmentSubmission assignmentSubmission, Principal principal){
        User user = userServicesImpl.getUserByUsername(principal.getName());
        CourseModules courseModules = courseModulesServicesImpl.getCourseModulesByCourseModulesId(assignmentSubmission.getAssignment().getAssignmentId());
        assignmentSubmission.setUser(user);
        assignmentSubmission.setAssignment(courseModules.getAssignment());
        try {
            assignmentSubmissionServices.addUpdateSubmission(assignmentSubmission);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Nộp bài thất bại", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Nộp bài thành công",HttpStatus.OK);
    }
}

package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.model.UserEnrolments;
import com.wfh.sp21.lms.model.UserEnrolmentsKey;
import com.wfh.sp21.lms.repository.UserEnrolmentsRepository;
import com.wfh.sp21.lms.services.UserEnrolmentsServices;
import com.wfh.sp21.lms.services.UserServices;
import com.wfh.sp21.lms.services.impl.CourseServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserEnrolmentsServicesImpl implements UserEnrolmentsServices {

    @Autowired
    private UserEnrolmentsRepository userEnrolmentsRepository;

    @Autowired
    private CourseServicesImpl courseServices;

    @Autowired
    private UserServices userServices;

    @Override
    public List<User> getAllEnrolmentsByCourseId(Long courseId) {
        List<UserEnrolments> list = userEnrolmentsRepository.findAllByCourse_CourseId(courseId);
        return list.stream().map(t ->{
            User tmp = t.getUser();
            tmp.setJoinedDate(t.getDateEnrolled());
            return tmp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Course> getAllEnrolmentsByUsername(String username) {
        List<UserEnrolments> list = userEnrolmentsRepository.findAllByUser_Username(username);
        return list.stream().map(UserEnrolments::getCourse).collect(Collectors.toList());
    }

    @Override
    public boolean addEnrolment(String username, Long courseId) {
        User user = userServices.getUserByUsername(username);
        Course course = courseServices.getCourseById(courseId);
        UserEnrolments userEnrolments = UserEnrolments.builder()
                .id(UserEnrolmentsKey.builder().username(user.getUsername()).courseId(course.getCourseId()).build())
                .user(user)
                .course(course)
                .dateEnrolled(new Date())
                .status(true)
                .build();
        userEnrolmentsRepository.save(userEnrolments);
        return true;
    }

    @Override
    public boolean addEnrolment(Long courseId, List<String> listUsernames) {
        for (String username: listUsernames) {
            addEnrolment(username,courseId);
        }
        return true;
    }

    @Override
    public boolean deleteEnrolment(String username, Long courseId) {
        UserEnrolmentsKey userEnrolmentsKey = UserEnrolmentsKey.builder().username(username).courseId(courseId).build();
        userEnrolmentsRepository.deleteById(userEnrolmentsKey);
        return true;
    }

    @Override
    public boolean deleteEnrolment(List<String> listUsernames, Long courseId) {
        for (String username:listUsernames) {
            deleteEnrolment(username, courseId);
        }
        return true;
    }
}

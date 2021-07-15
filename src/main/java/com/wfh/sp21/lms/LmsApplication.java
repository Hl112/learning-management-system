package com.wfh.sp21.lms;

import com.wfh.sp21.lms.model.CourseCategory;
import com.wfh.sp21.lms.model.Role;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.repository.CourseCategoryRepository;
import com.wfh.sp21.lms.repository.RoleRepository;
import com.wfh.sp21.lms.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
@EnableConfigurationProperties
public class LmsApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(LmsApplication.class, args);
        initUser(context);
        initCourse(context);
    }


    public static void initUser(ApplicationContext context) {
        RoleRepository repository = context.getBean(RoleRepository.class);
        Role admin = Role.builder().roleName("Admin").roleDescription("Managing accounts, role and permisions").build();
        Role teacher = Role.builder().roleName("Teacher").roleDescription("Managing course, resource, quiz, question, grade and student in course").build();
        Role student = Role.builder().roleName("Student").roleDescription("Learning, Do exercises, Access resource").build();
        admin = repository.save(admin);
        teacher = repository.save(teacher);
        student = repository.save(student);
        System.out.println(admin);
        System.out.println(teacher);
        System.out.println(student);
        UserRepository userRepository = context.getBean(UserRepository.class);
        User adminUser = User.builder().username("admin").password("1").lastName("Ho").firstName("Lam").email("Yuu@gmail.com").status(true).joinedDate(new Date()).role(admin).build();
        User teacherUser = User.builder().username("teacher").password("1").lastName("Tung").firstName("Nhi").email("Yuu1@gmail.com").status(true).joinedDate(new Date()).role(teacher).build();
        User studentUser = User.builder().username("student").password("1").lastName("Ho").firstName("Hoang").email("Yuu2@gmail.com").status(true).joinedDate(new Date()).role(student).build();
        adminUser = userRepository.save(adminUser);
        teacherUser = userRepository.save(teacherUser);
        studentUser = userRepository.save(studentUser);
        System.out.println("------------  Init User");


    }

    public static void initCourse(ApplicationContext context) {
        CourseCategoryRepository courseCategoryRepository = context.getBean(CourseCategoryRepository.class);
        CourseCategory courseCategory = CourseCategory.builder().categoryName("Công nghệ thông tin").categoryDescription("Software Engineer").build();
        CourseCategory courseCategory1 = CourseCategory.builder().categoryName("Ngôn ngữ anh").categoryDescription("Ngành ngôn ngữ").build();
        CourseCategory courseCategory2 = CourseCategory.builder().categoryName("Quản trị kinh doanh").categoryDescription("Ngành quản trị").build();
        courseCategoryRepository.save(courseCategory);
        courseCategoryRepository.save(courseCategory1);
        courseCategoryRepository.save(courseCategory2);

        System.out.println("---------- Init Course");
    }
}

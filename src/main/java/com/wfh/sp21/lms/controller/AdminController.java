package com.wfh.sp21.lms.controller;

import com.wfh.sp21.lms.model.Role;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.services.RoleServicesImpl;
import com.wfh.sp21.lms.services.UserServicesImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PropertySource("classpath:adminConfig.properties")
@Data
public class AdminController {
    @Value("${lms.admin.defaultPassword}")
    private String defaultPassword;

    @Autowired
    private UserServicesImpl userServicesImpl;

    @Autowired
    private RoleServicesImpl roleServicesImpl;
    //Init Attriute
    @ModelAttribute("userLogin")
    User userLogin(Principal principal) {
        return userServicesImpl.getUserByUsername(principal.getName());
    }
    //Page
    @GetMapping(value = {"","/"})
    public String adminPage(Model model,Principal principal){
        model.addAttribute("module","home");
        User user = userServicesImpl.getUserByUsername(principal.getName());
        model.addAttribute("userLogin",user);
        return "admin/admin";
    }
    //Manage Account
    @GetMapping("/userList")
    public String userListPage(Model model){
        List<User> userList = userServicesImpl.getAllUsers();
        List<Role> roleList = roleServicesImpl.getAllRoleList();
        List<String> classList = new ArrayList<>(List.of("bg-light-warning text-warning",
                "bg-light-danger text-danger",
                " bg-light-primary text-primary",
                "bg-light-info text-info",
                "bg-light-success text-success"));
        model.addAttribute("LIST_USER",userList);
        model.addAttribute("LIST_ROLE", roleList);
        model.addAttribute("CLASS_IMG", classList);
        model.addAttribute("module","userList");
        return "admin/userList";
    }

    @GetMapping("/userList/{username}")
    public String userDetailsPage(@PathVariable("username") String username, Model model){
        System.out.println(username);
        User user = userServicesImpl.getUserByUsername(username);
        if(user == null) return "404";
        model.addAttribute("user", user);
        model.addAttribute("module","userDetails");
        return "admin/userDetails";
    }

    @GetMapping("/addUser")
    public String addUserPage(Model model){
        List<Role> roleList = roleServicesImpl.getAllRoleList();
        model.addAttribute("LIST_ROLE", roleList);
        model.addAttribute("newUser", new User());
        model.addAttribute("module","addUser");
        model.addAttribute("DEFAULT_PASS", defaultPassword);
        return "admin/addUser";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody User user){
        boolean result = userServicesImpl.addUser(user);
        if(result){
            return new ResponseEntity<String>("Thêm thành viên thành công", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Tên tài khoản đã tồn tại", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUser")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody List<String> user) throws SQLException {
        boolean result = false;
        result = userServicesImpl.deleteUserByUsername(user);
        if(result){
            return new ResponseEntity<String>("Xóa thành viên thành công", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Xóa thành viên thất bại", HttpStatus.BAD_REQUEST);
        }
    }
    //Permision
    @GetMapping("/permission")
    public String permission(Model model){
        List<Role> roleList = roleServicesImpl.getAllRoleList();
        List<User> userList = userServicesImpl.getAllUsers();
        model.addAttribute("module","permission");
        model.addAttribute("LIST_USER",userList);
        model.addAttribute("LIST_ROLE", roleList);
        return "admin/permission";
    }

    @PutMapping("/permission")
    @ResponseBody
    public ResponseEntity<Object> assignRole(@RequestBody User user){
        if(userServicesImpl.changeRole(user)){
            return new ResponseEntity<Object>("Cập nhật vai trò thành công", HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("Cập nhật vai trò thất bại", HttpStatus.BAD_REQUEST);
        }
    }



}

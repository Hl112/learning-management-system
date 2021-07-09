package com.wfh.sp21.lms.controller;

import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class MainController {

    @Autowired
    private UserServices userServices;


    @GetMapping(value = {"/","/login"})
    public String loginPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(authentication.isAuthenticated()){
          return "redirect:/afterLogin";
      }
        return "login";
    }

    @GetMapping("/register")
    public String signUpPage() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerAccount(@RequestBody User user){
        boolean result = userServices.registerUser(user);
        if(result){
            return new ResponseEntity<String>("Bạn đã tạo tài khoản thành công", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Tên tài khoản đã tồn tại", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "403";
    }

    @RequestMapping("/afterLogin")
    public String defaultAfterLogin(HttpServletRequest request, Model model){
      Collection<? extends GrantedAuthority> authorities;
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      authorities =  authentication.getAuthorities();
      String role = authorities.toArray()[0].toString();
      if("Admin".equals(role)) return "redirect:/admin";
      if("Teacher".equals(role)) return "redirect:/teacher";
      if("Student".equals(role)) return "redirect:/student";
      model.addAttribute("Noti","Bạn phải đăng nhập để tiếp tục");
      return "login";
    }




}

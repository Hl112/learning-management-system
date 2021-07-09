package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.CustomUserDetails;
import com.wfh.sp21.lms.model.Role;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.repository.RoleRepository;
import com.wfh.sp21.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private static final String STUDENT_ROLE_NAME = "Student";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new CustomUserDetails(user);
    }

    public boolean registerUser(User user) {
        User userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null)
            return false;
        else {
            Role studentRole = roleRepository.findByRoleName(STUDENT_ROLE_NAME);
            user.setRole(studentRole);
            userRepository.save(user);
            return true;
        }
    }


}

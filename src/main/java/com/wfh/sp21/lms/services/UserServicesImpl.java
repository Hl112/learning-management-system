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

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServicesImpl implements UserDetailsService, UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private static final String STUDENT_ROLE_NAME = "Student";

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

    public boolean addUser(User user) {
        User userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null)
            return false;
        else {
            Role studentRole = roleRepository.findByRoleName(user.getRole().getRoleName());
            user.setRole(studentRole);
            userRepository.save(user);
            return true;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByStatusTrue();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean deleteUserByUsername(List<String> usernames) {
        for (String username: usernames) {
            User user = userRepository.findByUsername(username);
            if (user == null) return false;
            userRepository.delete(user);
        }
        return true;
    }

    public boolean deactivateListUsers(List<String> usernames) throws SQLException {
        boolean result = false;

           for (String username : usernames) {
               User user = userRepository.findByUsername(username);
               if (user == null)
                 result = false;
               user.setStatus(false);
               userRepository.save(user);
               result = true;
           }
        return result;
    }

    public boolean changeRole(User user){
        Role role = roleRepository.findByRoleName(user.getRole().getRoleName());
        User userDB = userRepository.findByUsername(user.getUsername());
        userDB.setRole(role);
        userRepository.save(userDB);
        return true;
    }


}

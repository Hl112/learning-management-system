package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserServices {
    boolean registerUser(User user);
    boolean addUser(User user);
    List<User> getAllUsers();
    User getUserByUsername(String username);
    boolean deleteUserByUsername(List<String> usernames);
    boolean changeRole(User user);
    boolean deactivateListUsers(List<String> usernames) throws SQLException;

}

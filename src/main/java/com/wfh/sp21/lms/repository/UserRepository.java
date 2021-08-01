package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.Course;
import com.wfh.sp21.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    List<User> findAllByStatusTrue();
    List<User> findAllByRole_RoleNameAndUsernameNotIn(String roleName, List<String> listUsername);
}

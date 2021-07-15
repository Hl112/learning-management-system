package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String username);

    public List<User> findAllByStatusTrue();

}

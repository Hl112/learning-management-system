package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.Role;

import java.util.List;

public interface RoleServices {
    List<Role> getAllRoleList();
    Role findRoleByName(String roleName);
}

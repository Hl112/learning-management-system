package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.Role;
import com.wfh.sp21.lms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServicesImpl implements RoleServices{
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoleList(){
        return roleRepository.findAll();
    }
    public Role findRoleByName(String roleName){ return  roleRepository.findByRoleName(roleName);}
}

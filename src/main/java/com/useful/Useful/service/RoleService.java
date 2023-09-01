package com.useful.Useful.service;

import com.useful.Useful.entity.Role;
import com.useful.Useful.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    RoleRepo repo;
    public void createNewRole(String name){
        Role newRole = new Role();
        newRole.setName(name);
        repo.save(newRole);
    }

    public void getRoleById(Long id){

    }

    public void getRoleByName(String name){

    }
}

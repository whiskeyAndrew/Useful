package com.useful.Useful.service;

import com.useful.Useful.Exceptions.RoleExistsException;
import com.useful.Useful.Exceptions.RoleNotFoundException;
import com.useful.Useful.entity.Role;
import com.useful.Useful.repository.RoleRepo;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class RoleService {
    private final RoleRepo repo;
    public void createNewRole(String name) throws RoleExistsException {
        if(repo.getRoleByName(name)!=null){
            throw new RoleExistsException("Role already existst");
        }
        Role newRole = new Role();
        newRole.setName(name);
        repo.save(newRole);
    }

    public Role getRoleById(Long id) throws RoleNotFoundException{
        Role role = repo.getRoleById(id);
        if(role==null){
            throw new RoleNotFoundException("No roles by this name");
        }
        return role;
    }

    public Role getRoleByName(String name) throws RoleNotFoundException {
        Role role = repo.getRoleByName(name);
        if(role==null){
            throw new RoleNotFoundException("No roles by this name");
        }
        return role;
    }

    public void deleteRole(Role role){
        repo.deleteById(role.getId());
    }
    public List<Role> getAllExistsRoles(){
        return repo.getAllByIdGreaterThan(-1L);
    }
    @PostConstruct
    public void initSimpleRoles(){
        try{
            if (getRoleByName("ROLE_USER") == null) {
                createNewRole("ROLE_USER");
                createNewRole("ROLE_ADMIN");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getShitcurityNamedRole(Role role) {
        return "ROLES_" + role.getName();
    }

    public String getUnshitcuritiedNamedRole(Role role) {
        String raw = role.getName();
        raw = raw.substring(5);
        return raw;
    }
}

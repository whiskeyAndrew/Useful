package com.useful.Useful.repository;

import com.useful.Useful.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllByIdGreaterThan(Long id);

    void deleteById(Long id);
}

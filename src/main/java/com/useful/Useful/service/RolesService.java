package com.useful.Useful.service;

import com.useful.Useful.entity.Roles;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    public String setupRoleAsSpringString(Roles ROLE) {
        return "ROLES_" + ROLE.toString();
    }

    public String getRawRole(Roles role) {
        String raw = role.toString();
        raw = raw.substring(5);
        return raw;
    }
}

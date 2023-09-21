package com.useful.Useful.controllers;

import com.useful.Useful.entity.Person;
import com.useful.Useful.entity.Role;
import com.useful.Useful.service.PersonService;
import com.useful.Useful.service.RoleService;
import com.useful.Useful.service.springShitcurity.SecurityUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonsListController {
    private final PersonService personService;
    private final SecurityUpdateService securityUpdateService;
    private final RoleService roleService;

    @GetMapping("/allUsersPage")
    public String getAllUsersList(Authentication authentication, Model model) {
        List<Person> personList = personService.getAllPerson();
        model.addAttribute("users", personList);
        boolean isAdmin = false;
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
        }
        personList.sort((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("roles", roleService.getAllExistsRoles());
        return "allUsersPage";
    }

    @PostMapping("/allUsersPage/user/{id}")
    public String updateRole(@RequestParam(name = "selectedRole") String newRole,
                             @PathVariable(name = "id") Long personId) {
        System.out.println(newRole + " " + personId);
        try {
            Role role = roleService.getRoleByName(newRole);
            Person person = personService.findPersonById(personId);
            if (!person.getRole().equals(role)) {
                person.setRole(role);
                personService.updatePerson(person);
            }
            securityUpdateService.forceUpdateUser(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/allUsersPage";
    }
}



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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class PersonsController {
    private final PersonService personService;
    private final SecurityUpdateService securityUpdateService;
    private final RoleService roleService;

    @GetMapping()
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

    @PostMapping("/edit/{id}")
    public String updateRole(@RequestParam(name = "selectedRole") String newRole,
                             @RequestParam(name = "name") String newUsername,
                             @PathVariable(name = "id") Long personId) {
        System.out.println(newRole + " " + personId);
        try {
            Role role = roleService.getRoleByName(newRole);
            Person person = personService.findPersonById(personId);
            if (!person.getRole().equals(role)) {
                person.setRole(role);
            }
            if (!person.getUsername().equals(newUsername)) {
                person.setUsername(newUsername);
            }
            personService.updatePerson(person);
            //Апдейт роли временно не работает
            //securityUpdateService.forceUpdateUser(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/users/edit/{id}";
    }

    @GetMapping("/edit/{id}")
    public String getUserEditData(@PathVariable(name = "id") Long personId,
                                  Model model) {
        try {
            Person person = personService.findPersonById(personId);
            model.addAttribute("person", person);
            model.addAttribute("roles", roleService.getAllExistsRoles());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/userAdminEdit";
    }
}



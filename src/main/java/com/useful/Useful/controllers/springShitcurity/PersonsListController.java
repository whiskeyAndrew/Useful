package com.useful.Useful.controllers.springShitcurity;

import com.useful.Useful.entity.Person;
import com.useful.Useful.entity.Roles;
import com.useful.Useful.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonsListController {
    private final PersonService personService;
    @GetMapping("/allUsersPage")
    public String getAllUsersList(Authentication authentication, Model model){
        List<Person> personList = personService.getAllPerson();
        model.addAttribute("users",personList);
        boolean isAdmin = false;
        for (GrantedAuthority authority:authentication.getAuthorities()) {
            if(authority.getAuthority().equals(Roles.ROLE_ADMIN.toString())){
                isAdmin = true;
            }
        }
        model.addAttribute("isAdmin",isAdmin);
        return "allUsersPage";
    }
}

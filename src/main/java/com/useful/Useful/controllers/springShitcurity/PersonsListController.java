package com.useful.Useful.controllers.springShitcurity;

import com.useful.Useful.Exceptions.PersonNotFoundException;
import com.useful.Useful.entity.Person;
import com.useful.Useful.entity.Roles;
import com.useful.Useful.service.PersonService;
import com.useful.Useful.service.springShitcurity.CustomUserDetailsService;
import com.useful.Useful.service.springShitcurity.SecurityUpdateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class PersonsListController {
    private final PersonService personService;
    private final SecurityUpdateService securityUpdateService;

    @GetMapping("/allUsersPage")
    public String getAllUsersList(Authentication authentication, Model model) {
        List<Person> personList = personService.getAllPerson();
        model.addAttribute("users", personList);
        boolean isAdmin = false;
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(Roles.ROLE_ADMIN.toString())) {
                isAdmin = true;
            }
        }
        personList.sort((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("roles", Roles.values());
        return "allUsersPage";
    }

    @PostMapping("/allUsersPage/user/{id}")
    public String updateRole(@RequestParam(name="selectedRole") String newRole,
                             @PathVariable(name = "id") Long personId) {
        System.out.println(newRole + " " +personId);
        Roles role = Roles.valueOf(newRole);
        try {
            Person person = personService.findPersonById(personId);
            if(!person.getRoles().contains(role)){
                person.getRoles().clear();
                person.getRoles().add(role);
                personService.updatePerson(person);
            }
            securityUpdateService.forceUpdateUser(person);
        }catch (PersonNotFoundException e){
            e.printStackTrace();
        }
        return "redirect:/allUsersPage";
    }
}



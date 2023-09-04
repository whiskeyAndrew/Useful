package com.useful.Useful.controllers.springShitcurity;

import com.useful.Useful.DTO.PersonDTO;
import com.useful.Useful.entity.Role;
import com.useful.Useful.service.PersonService;
import com.useful.Useful.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final PersonService personService;
    private final RoleService roleService;
    @PostMapping("/registration")
    private String regUser(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           Model model) {
        System.out.println(username + " " + password);
        if (personService.isPersonExistsByUsername(username)) {
            //Ошибка
            model.addAttribute("usernameExists", true);
            return "registration";
        }
        Role role = null;
        try {
            if (personService.getAllPerson().isEmpty()) {
                role = roleService.getRoleByName("ROLE_ADMIN");
            } else {
                role = roleService.getRoleByName("ROLE_USER");
            }
        } catch (Exception e){
            e.printStackTrace();
            return "redirect:login";
        }
        personService.savePerson(PersonDTO.builder()
                .username(username)
                .password(password)
                //Маленький костыль
                .role(role)
                .build());
        return "redirect:login";
    }

    @GetMapping("/registration")
    private String getRegUserPage() {
        return "/registration";
    }

}

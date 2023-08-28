package com.useful.Useful.controllers.springShitcurity;

import com.useful.Useful.DTO.PersonDTO;
import com.useful.Useful.entity.Roles;
import com.useful.Useful.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final PersonService personService;

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
        ArrayList<Roles> roles = new ArrayList<>();
        roles.add(Roles.ROLE_USER);
        personService.savePerson(PersonDTO.builder()
                .username(username)
                .password(password)
                .roles(roles)
                .build());
        return "redirect:login";
    }

    @GetMapping("/registration")
    private String getRegUserPage() {
        return "/registration";
    }

}

package com.useful.Useful.controllers;

import com.useful.Useful.DTO.PersonDTO;
import com.useful.Useful.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final PersonService personService;

    @PostMapping("/registration")
    private String regUser(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password) {
        System.out.println(username + " " + password);
        personService.savePerson(PersonDTO.builder()
                .username(username)
                .password(password)
                .build());
        return "/hello";
    }

    @GetMapping("/registration")
    private String getRegUserPage() {
        return "registration";
    }

}

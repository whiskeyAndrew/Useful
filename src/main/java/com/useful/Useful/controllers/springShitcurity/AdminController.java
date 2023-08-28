package com.useful.Useful.controllers.springShitcurity;

import com.useful.Useful.entity.Person;
import com.useful.Useful.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PersonService personService;

    @GetMapping("/helloAdmin")
    public String adminHelloPage(Authentication authentication){
        return "/admin/helloAdmin";
    }

//    @GetMapping("/allUsersPage")
//    public String allUsersPage(Model model){
//        List<Person> personList = personService.getAllPerson();
//        model.addAttribute("persons",personList);
//        return "/admin/allUsersPage";
//    }
}

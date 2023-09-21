package com.useful.Useful.controllers.springShitcurity;

import com.useful.Useful.entity.Person;
import com.useful.Useful.entity.Role;
import com.useful.Useful.service.PersonService;
import com.useful.Useful.service.RoleService;
import com.useful.Useful.service.springShitcurity.SecurityUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/rolesPanel")
@RequiredArgsConstructor
public class RolesPanelController {
    private final RoleService roleService;
    private final PersonService personService;
    private final SecurityUpdateService securityUpdateService;

    @GetMapping()
    public String getRolesPanel(Model model) {
        model.addAttribute("availableRoles", roleService.getAllExistsRoles());
        return "/admin/rolesPanel";
    }

    @PostMapping()
    public String updateRoles(@RequestParam(name = "roleName") String newRoleName) {
        if (!newRoleName.startsWith("ROLE_")) {
            //грязь
            newRoleName = "ROLE_" + newRoleName;
        }
        try {
            roleService.createNewRole(newRoleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/rolesPanel";
    }

    @PostMapping("/delete/{id}")
    public String deleteRole(@PathVariable(name = "id") Long roleId) {
        Role deletableRole;
        Role newRole;
        try {
            deletableRole = roleService.getRoleById(roleId);
            newRole = roleService.getRoleById(1L);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        List<Person> personWithDeletableRoleList = personService.findAllByRole(deletableRole);
        for (Person person : personWithDeletableRoleList) {
            person.setRole(newRole);
            personService.updatePerson(person);
            securityUpdateService.forceUpdateUser(person);
        }

        roleService.deleteRole(deletableRole);
        return "redirect:/admin/rolesPanel";
    }
}

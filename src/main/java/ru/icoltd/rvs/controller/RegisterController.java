package ru.icoltd.rvs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.icoltd.rvs.dtos.UserDto;
import ru.icoltd.rvs.service.RoleService;
import ru.icoltd.rvs.service.UserService;
import ru.icoltd.rvs.util.role.RoleUtils;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("regUser", UserDto.builder().build());
        model.addAttribute("roleNames", RoleUtils.getRoleNames(roleService.findAll()));
        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("regUser") UserDto regUser,
                                      BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roleNames", roleService.findAll());
            return "registration-form";
        } else {
            UserDto saved = userService.saveUser(regUser, passwordEncoder);
            log.info("User '{}' was registered successful.", saved.getUsername());
            return "confirmation-page";
        }
    }
}

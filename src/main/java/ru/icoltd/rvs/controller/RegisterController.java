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
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.RoleService;
import ru.icoltd.rvs.service.UserService;
import ru.icoltd.rvs.user.RegisteredUser;
import ru.icoltd.rvs.util.role.RoleUtils;

import javax.validation.Valid;
import java.util.Optional;

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
        model.addAttribute("regUser", new RegisteredUser());
        model.addAttribute("roleNames", RoleUtils.getRoleNames(roleService.findAll()));
        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("regUser") RegisteredUser regUser,
                                      BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roleNames", roleService.findAll());
            return "registration-form";
        } else {
            User user = Optional.of(regUser).map(
                    (u) -> new User(
                            u.getUserName(),
                            passwordEncoder.encode(u.getPassword()),
                            u.getFirstName(),
                            u.getLastName(),
                            u.getEmail(),
                            u.getDateOfBirth(),
                            roleService.findByName(u.getRoles()))
            ).get();
            userService.saveUser(user);
            log.info("User '{}' was registered successful.", user.getUsername());
            return "confirmation-page";
        }
    }
}

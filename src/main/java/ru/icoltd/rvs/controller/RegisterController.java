package ru.icoltd.rvs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.UserService;
import ru.icoltd.rvs.user.RegisteredUser;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(false);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("regUser", new RegisteredUser());
        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("regUser") RegisteredUser regUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration-form";
        } else {
            User user = Optional.of(regUser).map(
                    (u) -> new User(
                            u.getUserName(),
                            passwordEncoder.encode(u.getPassword()),
                            u.getFirstName(),
                            u.getLastName(),
                            u.getEmail(),
                            u.getDateOfBirth()
                    )
            ).get();
            userService.saveUser(user);
            log.info("User '{}' was registered successful.", user.getUserName());
            return "confirmation-page";
        }
    }
}

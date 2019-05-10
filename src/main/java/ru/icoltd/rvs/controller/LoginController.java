package ru.icoltd.rvs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/accessDenied")
    public String showAccessDenied() {

        return "access-denied";
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "plain-login";
    }
}

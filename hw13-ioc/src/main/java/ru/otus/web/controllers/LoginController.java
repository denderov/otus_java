package ru.otus.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.api.service.DBServiceUser;
import ru.otus.web.services.UserAuthService;

@Controller
public class LoginController {

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private final UserAuthService userAuthService;
    private final DBServiceUser dbServiceUser;

    public LoginController(UserAuthService userAuthService, DBServiceUser dbServiceUser) {
        this.userAuthService = userAuthService;
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping({"/"})
    public String loginView(Model model) {
        model.addAttribute(PARAM_LOGIN, "");
        model.addAttribute(PARAM_PASSWORD, "");
        return "login.html";
    }

    @PostMapping("/login")
    public RedirectView userSave(@ModelAttribute String login, String password) {

        if (userAuthService.authenticate(login, password)) {
            return new RedirectView("/users", true);
        } else {
            return new RedirectView("/", true);
        }

    }
}

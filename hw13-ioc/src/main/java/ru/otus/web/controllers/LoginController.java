package ru.otus.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.web.services.UserAuthService;

@Controller
public class LoginController {

    private final UserAuthService userAuthService;

    public LoginController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @GetMapping({"/"})
    public String loginView(Model model) {
        model.addAttribute("loginUser", new LoginUser());
        return "login.html";
    }

    @PostMapping("/login")
    public RedirectView loginSubmit(@ModelAttribute LoginUser loginUser) {

        if (userAuthService.authenticate(loginUser.getLogin(), loginUser.getPassword())) {
            return new RedirectView("/users", true);
        } else {
            return new RedirectView("/", true);
        }

    }
}

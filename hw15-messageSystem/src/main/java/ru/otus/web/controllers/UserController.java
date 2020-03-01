package ru.otus.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.api.service.DBServiceUser;

import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final DBServiceUser serviceUser;

    public UserController(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping({"/users"})
    public String userListView(Model model) {
        List<ru.otus.api.model.User> users = serviceUser.getAll();
        model.addAttribute("users", users);
        return "users.html";
    }

    @GetMapping("/user/add")
    public String userCreateView(Model model) {
        model.addAttribute("user", new ru.otus.api.model.User());
        return "add_user.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute ru.otus.api.model.User user) {
        serviceUser.saveUser(user);
        return new RedirectView("/users", true);
    }

}

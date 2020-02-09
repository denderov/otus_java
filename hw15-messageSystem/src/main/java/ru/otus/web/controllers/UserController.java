package ru.otus.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;

import java.util.List;

@Controller
public class UserController {

    private final DBServiceUser serviceUser;

    public UserController(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping({"/users"})
    public String userListView(Model model) {
        List<User> users = serviceUser.getAll();
        model.addAttribute("users", users);
        return "users.html";
    }

    @GetMapping("/user/add")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "add_user.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        serviceUser.saveUser(user);
        return new RedirectView("/users", true);
    }

}

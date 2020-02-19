package ru.otus.web.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.api.service.DBServiceUser;
import ru.otus.web.front.FrontendService;

import java.util.List;

@Controller
public class UserController {

    private final DBServiceUser serviceUser;

    private final FrontendService frontendService;

    private final SimpMessagingTemplate messagingTemplate;

    public UserController(DBServiceUser serviceUser, FrontendService frontendService, SimpMessagingTemplate messagingTemplate) {
        this.serviceUser = serviceUser;
        this.frontendService = frontendService;
        this.messagingTemplate = messagingTemplate;
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

    @MessageMapping("/createUser")
    public void createUser(String userJson) {
        frontendService.createUser(userJson, data -> this.messagingTemplate.convertAndSend("/topic/user", data));
    }



}

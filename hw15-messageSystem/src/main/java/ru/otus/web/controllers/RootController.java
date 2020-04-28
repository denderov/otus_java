package ru.otus.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.api.service.DBServiceUser;

@Controller
public class RootController {
    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    private final DBServiceUser serviceUser;

    public RootController(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping({"/"})
    public String userListView(Model model) {
        return "users.html";
    }
}

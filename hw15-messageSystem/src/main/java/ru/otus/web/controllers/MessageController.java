package ru.otus.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.web.front.FrontendService;

@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final FrontendService frontendService;

    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(FrontendService frontendService, SimpMessagingTemplate messagingTemplate) {
        this.frontendService = frontendService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/getAllUsers")
    @SendTo("/topic/allUsers")
    public void getAllUsers() {
        frontendService.getAllUserData(data ->
        {
            logger.info("all users: {}", data);
            messagingTemplate.convertAndSend("/topic/allUsers", data);
        });
    }

    @MessageMapping("/createUser")
    @SendTo("/topic/user")
    public void createUser(String userJson) {
        frontendService.createUser(userJson, data ->
        {
            logger.info("created user: {}", data);
            messagingTemplate.convertAndSend("/topic/user", data.toString());
        });
    }
}
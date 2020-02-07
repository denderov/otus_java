package ru.otus.web.controllers;

import lombok.Getter;
import lombok.Setter;

class LoginUser {
    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;
}

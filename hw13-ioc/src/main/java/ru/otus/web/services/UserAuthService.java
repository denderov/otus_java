package ru.otus.web.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}

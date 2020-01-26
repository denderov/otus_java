package ru.otus.web.services;

import org.springframework.stereotype.Service;

@Service
public interface UserAuthService {
    boolean authenticate(String login, String password);
}

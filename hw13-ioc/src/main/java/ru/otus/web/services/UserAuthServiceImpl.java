package ru.otus.web.services;

import org.springframework.stereotype.Service;
import ru.otus.api.service.DBServiceUser;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceUser dbServiceUser;

    public UserAuthServiceImpl(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return dbServiceUser.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}

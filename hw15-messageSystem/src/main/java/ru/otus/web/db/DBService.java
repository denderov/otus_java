package ru.otus.web.db;


import ru.otus.api.model.User;

public interface DBService {
    long saveUser(User user);
}

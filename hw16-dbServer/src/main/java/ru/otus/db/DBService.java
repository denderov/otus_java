package ru.otus.db;


import com.fasterxml.jackson.core.JsonProcessingException;
import ru.otus.api.model.User;

public interface DBService {
    long saveUser(User user);

    String getAllUsers() throws JsonProcessingException;
}

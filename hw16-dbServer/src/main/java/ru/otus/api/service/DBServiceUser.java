package ru.otus.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.otus.api.model.User;

@Service
public interface DBServiceUser {

  long saveUser(User user);

  Optional<User> getUser(long id);

  Optional<User> findByLogin(String login);

  List<User> getAll();
}

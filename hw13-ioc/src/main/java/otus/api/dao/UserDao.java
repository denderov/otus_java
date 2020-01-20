package otus.api.dao;

import ru.otus.api.model.User;
import ru.otus.api.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {
  Optional<User> findById(long id);

  long saveUser(User user);

  SessionManager getSessionManager();

  Optional<User> findByLogin(String login);

  List<User> getAll();
}

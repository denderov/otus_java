package ru.otus.web.front;


import ru.otus.api.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {
  void getUserData(long userId, Consumer<String> dataConsumer);

  void createUser(User user, Consumer<String> dataConsumer);

  <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}


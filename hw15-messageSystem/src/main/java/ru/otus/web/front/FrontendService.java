package ru.otus.web.front;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {

  void getAllUserData(long userId, Consumer<String> dataConsumer);

  void getUserData(long userId, Consumer<String> dataConsumer);

  void createUser(String userJson, Consumer<Long> dataConsumer);

  <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}


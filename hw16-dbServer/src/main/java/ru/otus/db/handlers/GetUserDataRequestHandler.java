package ru.otus.db.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import lombok.SneakyThrows;
import ru.otus.api.model.User;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.common.Serializers;
import ru.otus.db.DBService;
import ru.otus.messagesystem.MessageType;


public class GetUserDataRequestHandler implements RequestHandler {
  private final DBService dbService;
  private final ObjectMapper objectMapper;

  public GetUserDataRequestHandler(DBService dbService, ObjectMapper objectMapper) {
    this.dbService = dbService;
    this.objectMapper = objectMapper;
  }

  @SneakyThrows
  @Override
  public Optional<Message> handle(Message msg) {
    User user = objectMapper.readValue(Serializers.deserialize(msg.getPayload(), String.class),User.class);
    long data = dbService.saveUser(user);
    return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
  }
}

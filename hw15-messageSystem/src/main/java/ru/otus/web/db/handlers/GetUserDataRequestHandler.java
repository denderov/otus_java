package ru.otus.web.db.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.api.model.User;
import ru.otus.web.common.Serializers;
import ru.otus.web.db.DBService;
import ru.otus.web.messagesystem.Message;
import ru.otus.web.messagesystem.MessageType;
import ru.otus.web.messagesystem.RequestHandler;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler {
  private final DBService dbService;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public GetUserDataRequestHandler(DBService dbService) {
    this.dbService = dbService;
  }

  @Override
  public Optional<Message> handle(Message msg) throws JsonProcessingException {
    User user = objectMapper.readValue(Serializers.deserialize(msg.getPayload(), String.class),User.class);
    long data = dbService.saveUser(user);
    return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
  }
}

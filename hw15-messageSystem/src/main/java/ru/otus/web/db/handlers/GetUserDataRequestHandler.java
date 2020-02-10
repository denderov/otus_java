package ru.otus.web.db.handlers;

import ru.otus.web.common.Serializers;
import ru.otus.web.db.DBService;
import ru.otus.web.messagesystem.Message;
import ru.otus.web.messagesystem.MessageType;
import ru.otus.web.messagesystem.RequestHandler;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler {
  private final DBService dbService;

  public GetUserDataRequestHandler(DBService dbService) {
    this.dbService = dbService;
  }

  @Override
  public Optional<Message> handle(Message msg) {
    long id = Serializers.deserialize(msg.getPayload(), Long.class);
    String data = dbService.getUserData(id);
    return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
  }
}

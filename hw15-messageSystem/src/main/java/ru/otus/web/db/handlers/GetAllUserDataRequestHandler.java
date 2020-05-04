package ru.otus.web.db.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.otus.web.common.Serializers;
import ru.otus.web.db.DBService;
import ru.otus.web.messagesystem.Message;
import ru.otus.web.messagesystem.MessageType;
import ru.otus.web.messagesystem.RequestHandler;

import java.util.Optional;

public class GetAllUserDataRequestHandler implements RequestHandler {
    private final DBService dbService;

    public GetAllUserDataRequestHandler(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) throws JsonProcessingException {
        String data = dbService.getAllUsers();
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.ALL_USERS_DATA.getValue(), Serializers.serialize(data)));
    }
}

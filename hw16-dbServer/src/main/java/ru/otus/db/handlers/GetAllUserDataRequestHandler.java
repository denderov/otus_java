package ru.otus.db.handlers;

import java.util.Optional;
import lombok.SneakyThrows;
import ru.otus.common.Serializers;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.db.DBService;

public class GetAllUserDataRequestHandler implements RequestHandler {
    private final DBService dbService;

    public GetAllUserDataRequestHandler(DBService dbService) {
        this.dbService = dbService;
    }

    @SneakyThrows
    @Override
    public Optional<Message> handle(Message msg) {
        String data = dbService.getAllUsers();
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.ALL_USERS_DATA.getValue(), Serializers
            .serialize(data)));
    }
}

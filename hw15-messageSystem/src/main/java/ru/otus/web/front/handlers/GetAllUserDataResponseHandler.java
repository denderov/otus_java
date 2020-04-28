package ru.otus.web.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.web.common.Serializers;
import ru.otus.web.front.FrontendService;
import ru.otus.web.messagesystem.Message;
import ru.otus.web.messagesystem.RequestHandler;

import java.util.Optional;
import java.util.UUID;

public class GetAllUserDataResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

    private final FrontendService frontendService;

    public GetAllUserDataResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);
        try {
            String allUsers = Serializers.deserialize(msg.getPayload(), String.class);
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
            frontendService.takeConsumer(sourceMessageId, String.class).ifPresent(consumer -> consumer.accept(allUsers));

        } catch (Exception ex) {
            logger.error("msg:" + msg, ex);
        }
        return Optional.empty();
    }
}

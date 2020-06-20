package ru.otus.messagesystem;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.socket.SocketClient;

public class GeneralRequestHandler implements RequestHandler {
  private static final Logger logger = LoggerFactory.getLogger(GeneralRequestHandler.class);

  private final SocketClient socketClient;

  public GeneralRequestHandler(SocketClient socketClient) {
    this.socketClient = socketClient;
  }

  @Override
  public Optional<Message> handle(Message msg) {
    logger.info("send message:{}", msg);
    socketClient.sendMessage(msg);
    return Optional.empty();
  }
}

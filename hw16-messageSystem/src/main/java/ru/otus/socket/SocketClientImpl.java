package ru.otus.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.messagesystem.Message;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientImpl implements SocketClient {
  private static final Logger logger = LoggerFactory.getLogger(SocketClientImpl.class);

  private final String host;
  private final int port;

  public SocketClientImpl(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void sendMessage(Message message) {
    logger.info("Message {} was received from {} to {}", message.getId(), message.getFrom(), message.getTo());
    logger.info("connected to the host {} on {} port", host, port);
    try (Socket clientSocket = new Socket(host, port);
        ObjectOutputStream outputStream = new ObjectOutputStream(
            new BufferedOutputStream(clientSocket.getOutputStream()))) {
      outputStream.writeObject(message);
      logger.info("Message {} was sent to {}", message.getId(), message.getTo());
    } catch (Exception e) {
      logger.error("error", e);
    }
  }
}

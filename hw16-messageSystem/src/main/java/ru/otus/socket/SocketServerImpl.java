package ru.otus.socket;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageSystem;

public class SocketServerImpl implements SocketServer {

  private static final Logger logger = LoggerFactory.getLogger(SocketServerImpl.class);
  private final MessageSystem messageSystem;
  private final int port;

  public SocketServerImpl(MessageSystem messageSystem, int port) {
    this.messageSystem = messageSystem;
    this.port = port;
  }

  @Override
  public void go() {
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      while (!Thread.currentThread().isInterrupted()) {
        SocketServerImpl.logger.info("waiting for client connection");
        try (Socket clientSocket = serverSocket.accept()) {
          clientHandler(clientSocket);
        }
      }
    } catch (Exception ex) {
      SocketServerImpl.logger.error("error", ex);
    }
  }

  @Override
  public void clientHandler(Socket clientSocket) {
    try (ObjectInputStream inputStream = new ObjectInputStream(
        new BufferedInputStream(clientSocket.getInputStream()))) {
      Message message = (Message) inputStream.readObject();
      SocketServerImpl.logger.info("Message {} was received from {} to {}", message.getId(), message.getFrom(),
          message.getTo());
      messageSystem.newMessage(message);
    } catch (Exception e) {
      SocketServerImpl.logger.error("error", e);
    }
  }

}

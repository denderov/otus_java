package ru.otus.socket;

import ru.otus.messagesystem.Message;

public interface SocketClient {

  void sendMessage(Message message);
}

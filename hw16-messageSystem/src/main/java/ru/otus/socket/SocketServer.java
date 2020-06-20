package ru.otus.socket;

import java.net.Socket;

public interface SocketServer {

  void go();

  void clientHandler(Socket clientSocket);
}

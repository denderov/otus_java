package ru.otus.web.server;

public interface UsersWebServer {
    void start() throws Exception;

    void join() throws Exception;

    void stop() throws Exception;
}

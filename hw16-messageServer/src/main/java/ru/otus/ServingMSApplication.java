package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.socket.SocketServer;

@SpringBootApplication
public class ServingMSApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ServingMSApplication.class, args);
        var socketServer = context.getBean(SocketServer.class);
        socketServer.go();
    }

}
package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.socket.SocketServer;

@SpringBootApplication
public class ServingDbApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ServingDbApplication.class, args);
        var socketServer = context.getBean(SocketServer.class);
        socketServer.go();
    }

}
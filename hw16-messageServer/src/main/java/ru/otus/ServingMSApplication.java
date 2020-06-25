package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.config.AppRunner;

@SpringBootApplication
public class ServingMSApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ServingMSApplication.class, args);
        var runner = context.getBean(AppRunner.class);
        runner.runAll();
//        var socketServer = context.getBean(SocketServer.class);
//        socketServer.go();
    }

}
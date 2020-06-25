package ru.otus.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.front.FrontendService;
import ru.otus.front.handlers.GetAllUserDataResponseHandler;
import ru.otus.front.handlers.GetUserDataResponseHandler;
import ru.otus.messagesystem.GeneralRequestHandler;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.MessageSystemImpl;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.MsClient;
import ru.otus.messagesystem.MsClientImpl;
import ru.otus.socket.SocketClient;
import ru.otus.socket.SocketClientImpl;
import ru.otus.socket.SocketServer;
import ru.otus.socket.SocketServerImpl;

@Configuration
public class AppConfig {

    @Bean(destroyMethod="dispose")
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public SocketClient socketClient(ApplicationArguments arguments) {
        return new SocketClientImpl("localhost",
            Integer.parseInt(arguments.getSourceArgs()[1])); //8085
    }

    @Bean
    public SocketServer socketServer(MessageSystem messageSystem, ApplicationArguments arguments) {
        return new SocketServerImpl(messageSystem,
            Integer.parseInt(arguments.getSourceArgs()[0])); //8087
    }

    @Bean
    public MsClient socketMsClient(MessageSystem messageSystem, SocketClient socketClient,
        ApplicationArguments arguments) {
        MsClient databaseMsClient = new MsClientImpl(arguments.getSourceArgs()[3], messageSystem); //DATABASE_SERVICE_CLIENT_NAME
        databaseMsClient
            .addHandler(MessageType.USER_DATA, new GeneralRequestHandler(socketClient));
        databaseMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GeneralRequestHandler(socketClient));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public MsClient frontendMsClient(MessageSystem messageSystem, FrontendService frontendService,
        ApplicationArguments arguments) {
        MsClient frontendMsClient = new MsClientImpl(arguments.getSourceArgs()[2], messageSystem); //FRONTEND_SERVICE_CLIENT_NAME
        frontendMsClient
            .addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
        frontendMsClient
            .addHandler(MessageType.ALL_USERS_DATA,
                new GetAllUserDataResponseHandler(frontendService));
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }

}

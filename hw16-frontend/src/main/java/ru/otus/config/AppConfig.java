package ru.otus.config;

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

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean(destroyMethod="dispose")
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public SocketClient socketClient() {
        return new SocketClientImpl("localhost", 8085);
    }

    @Bean
    public SocketServer socketServer(MessageSystem messageSystem) {
        return new SocketServerImpl(messageSystem,8086);
    }

    @Bean
    public MsClient socketMsClient(MessageSystem messageSystem,SocketClient socketClient) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        databaseMsClient
            .addHandler(MessageType.USER_DATA, new GeneralRequestHandler(socketClient));
        databaseMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GeneralRequestHandler(socketClient));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public MsClient frontendMsClient(MessageSystem messageSystem, FrontendService frontendService) {
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
        frontendMsClient
            .addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
        frontendMsClient
            .addHandler(MessageType.ALL_USERS_DATA,
                new GetAllUserDataResponseHandler(frontendService));
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }

}

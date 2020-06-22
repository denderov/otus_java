package ru.otus.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    private static final String FRONTEND_SERVICE_CLIENT_NAME_1 = "frontendService1";
    private static final String DATABASE_SERVICE_CLIENT_NAME_1 = "databaseService1";
    public static final int FRONTEND_CLIENT_PORT_1 = 8086;
    public static final int DATABASE_CLIENT_PORT_1 = 8087;
    private static final String FRONTEND_SERVICE_CLIENT_NAME_2 = "frontendService2";
    private static final String DATABASE_SERVICE_CLIENT_NAME_2 = "databaseService2";
    public static final int FRONTEND_CLIENT_PORT_2 = 8088;
    public static final int DATABASE_CLIENT_PORT_2 = 8089;

    @Bean(destroyMethod="dispose")
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public SocketClient dbSocketClient1() {
        return new SocketClientImpl("localhost", DATABASE_CLIENT_PORT_1);
    }

    @Bean
    public SocketClient frontendSocketClient1() {
        return new SocketClientImpl("localhost", FRONTEND_CLIENT_PORT_1);
    }

    @Bean
    public SocketClient dbSocketClient2() {
        return new SocketClientImpl("localhost", DATABASE_CLIENT_PORT_2);
    }

    @Bean
    public SocketClient frontendSocketClient2() {
        return new SocketClientImpl("localhost", FRONTEND_CLIENT_PORT_2);
    }

    @Bean
    public SocketServer socketServer(MessageSystem messageSystem) {
        return new SocketServerImpl(messageSystem,8085);
    }

    @Bean
    public MsClient dbMsClient1(MessageSystem messageSystem,
        @Qualifier("dbSocketClient1") SocketClient socketClient) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME_1, messageSystem);
        databaseMsClient
            .addHandler(MessageType.USER_DATA, new GeneralRequestHandler(socketClient));
        databaseMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GeneralRequestHandler(socketClient));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public MsClient frontendMsClient1(MessageSystem messageSystem,
        @Qualifier("frontendSocketClient1") SocketClient socketClient) {
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME_1, messageSystem);
        frontendMsClient
            .addHandler(MessageType.USER_DATA, new GeneralRequestHandler(socketClient));
        frontendMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GeneralRequestHandler(socketClient));
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }

    @Bean
    public MsClient dbMsClient2(MessageSystem messageSystem,
        @Qualifier("dbSocketClient2") SocketClient socketClient) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME_2, messageSystem);
        databaseMsClient
            .addHandler(MessageType.USER_DATA, new GeneralRequestHandler(socketClient));
        databaseMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GeneralRequestHandler(socketClient));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public MsClient frontendMsClient2(MessageSystem messageSystem,
        @Qualifier("frontendSocketClient2") SocketClient socketClient) {
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME_2, messageSystem);
        frontendMsClient
            .addHandler(MessageType.USER_DATA, new GeneralRequestHandler(socketClient));
        frontendMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GeneralRequestHandler(socketClient));
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }


}

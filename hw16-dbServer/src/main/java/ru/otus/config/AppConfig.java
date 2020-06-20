package ru.otus.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.api.model.User;
import ru.otus.api.sessionmanager.SessionManagerException;
import ru.otus.db.DBService;
import ru.otus.db.handlers.GetAllUserDataRequestHandler;
import ru.otus.db.handlers.GetUserDataRequestHandler;
import ru.otus.hibernate.HibernateUtils;
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

    @Bean
    public SessionFactory sessionFactory() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
            User.class);
        if (sessionFactory == null) {
            throw new SessionManagerException("SessionFactory is null");
        }
        return sessionFactory;
    }

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
        return new SocketServerImpl(messageSystem,8087);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public MsClient databaseMsClient(MessageSystem messageSystem, DBService dbService,
        ObjectMapper objectMapper) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        databaseMsClient.addHandler(MessageType.USER_DATA,
            new GetUserDataRequestHandler(dbService, objectMapper));
        databaseMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GetAllUserDataRequestHandler(dbService));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public MsClient frontendMsClient(MessageSystem messageSystem, SocketClient socketClient) {
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
        frontendMsClient
            .addHandler(MessageType.USER_DATA, new GeneralRequestHandler(socketClient));
        frontendMsClient
            .addHandler(MessageType.ALL_USERS_DATA, new GeneralRequestHandler(socketClient));
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }
}

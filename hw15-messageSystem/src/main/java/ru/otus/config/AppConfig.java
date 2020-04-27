package ru.otus.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;
import ru.otus.api.sessionmanager.SessionManagerException;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.web.db.DBService;
import ru.otus.web.db.handlers.GetAllUserDataRequestHandler;
import ru.otus.web.db.handlers.GetUserDataRequestHandler;
import ru.otus.web.front.FrontendService;
import ru.otus.web.front.handlers.GetAllUserDataResponseHandler;
import ru.otus.web.front.handlers.GetUserDataResponseHandler;
import ru.otus.web.helpers.DBInitializer;
import ru.otus.web.messagesystem.*;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean(initMethod="init")
    public DBInitializer initUsers(DBServiceUser dbServiceUser) {
        return new DBInitializer(dbServiceUser);
    }

    @Bean
    public SessionFactory sessionFactory() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                User.class);
        if (sessionFactory == null) {
            throw new SessionManagerException("SessionFactory is null");
        }
        return sessionFactory;
    }

    @Bean
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public MsClient databaseMsClient(MessageSystem messageSystem, DBService dbService) {
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        databaseMsClient.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbService));
        databaseMsClient.addHandler(MessageType.ALL_USERS_DATA, new GetAllUserDataRequestHandler(dbService));
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public MsClient frontendMsClient(MessageSystem messageSystem, FrontendService frontendService) {
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
        frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
        frontendMsClient.addHandler(MessageType.ALL_USERS_DATA, new GetAllUserDataResponseHandler(frontendService));
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }

}

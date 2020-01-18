package ru.otus;

import org.hibernate.SessionFactory;
import ru.otus.api.dao.UserDao;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;
import ru.otus.api.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.web.server.UsersWebServer;
import ru.otus.web.server.UsersWebServerImpl;
import ru.otus.web.services.TemplateProcessor;
import ru.otus.web.services.TemplateProcessorImpl;
import ru.otus.web.services.UserAuthService;
import ru.otus.web.services.UserAuthServiceImpl;

public class Main {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                User.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        makeDefaultUsers(dbServiceUser);

        UserAuthService userAuthServiceForFilterBasedSecurity = new UserAuthServiceImpl(dbServiceUser);

        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        UsersWebServer usersWebServer = new UsersWebServerImpl(WEB_SERVER_PORT,
                userAuthServiceForFilterBasedSecurity,
                dbServiceUser,
                templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static void makeDefaultUsers(DBServiceUser dbServiceUser) {
        User adminUser = new User("Vasya","vas","123");
        dbServiceUser.saveUser(adminUser);
        User anotherUser = new User("Ivan","ivan","123");
        dbServiceUser.saveUser(anotherUser);
    }
}

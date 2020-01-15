package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import ru.otus.api.dao.UserDao;
import ru.otus.api.model.AddressDataSet;
import ru.otus.api.model.PhoneDataSet;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;
import ru.otus.api.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.web.helpers.FileSystemHelper;
import ru.otus.web.server.UsersWebServer;
import ru.otus.web.server.UsersWebServerImpl;
import ru.otus.web.services.TemplateProcessor;
import ru.otus.web.services.TemplateProcessorImpl;
import ru.otus.web.services.UserAuthService;
import ru.otus.web.services.UserAuthServiceImpl;

import static ru.otus.web.server.SecurityType.FILTER_BASED;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class Main {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String REALM_NAME = "AnyRealm";

    public static void main(String[] args) throws Exception {
        String hashLoginServiceConfigPath = FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        User adminUser = new User("Vasya","vas",null,null);
        dbServiceUser.saveUser(adminUser);
        User anotherUser = new User("Ivan","iv",null,null);
        dbServiceUser.saveUser(anotherUser);

        UserAuthService userAuthServiceForFilterBasedSecurity = new UserAuthServiceImpl(dbServiceUser);

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        UsersWebServer usersWebServer = new UsersWebServerImpl(WEB_SERVER_PORT,
                FILTER_BASED,
                userAuthServiceForFilterBasedSecurity,
                null,
                dbServiceUser,
                gson,
                templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}

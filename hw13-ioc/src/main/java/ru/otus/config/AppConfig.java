package ru.otus.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;
import ru.otus.api.sessionmanager.SessionManagerException;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.web.helpers.DBInitializer;

@Configuration
@ComponentScan("ru.otus")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
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
}

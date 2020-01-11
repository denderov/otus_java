package ru.otus;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.dao.UserDao;
import ru.otus.api.model.AddressDataSet;
import ru.otus.api.model.PhoneDataSet;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;
import ru.otus.api.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class DbServiceDemo {
  private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

  public static void main(String[] args) {
    SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
            User.class, AddressDataSet.class, PhoneDataSet.class);

    SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
    UserDao userDao = new UserDaoHibernate(sessionManager);
    DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

    User user = new User("Вася"
            ,new AddressDataSet("Дерибасовская")
            , Arrays.asList(new PhoneDataSet("9999999999"),new PhoneDataSet("4959999999")));

    long id = dbServiceUser.saveUser(user);
    Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);

    user.setName("Vasya");
    user.setAddressDataSet(new AddressDataSet("Brighton Beach"));
    user.setPhoneDataSet(Collections.singleton(new PhoneDataSet("7189999999")));

    id = dbServiceUser.saveUser(user);
    Optional<User> mayBeUpdatedUser = dbServiceUser.getUser(id);

    outputUserOptional("Created user", mayBeCreatedUser);
    outputUserOptional("Updated user", mayBeUpdatedUser);

      Optional<User> tstUser = dbServiceUser.getUser(999L);
  }

  private static void outputUserOptional(String header, Optional<User> mayBeUser) {
    System.out.println("-----------------------------------------------------------");
    System.out.println(header);
    mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
  }
}

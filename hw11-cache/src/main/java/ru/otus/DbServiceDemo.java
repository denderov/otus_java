package ru.otus;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.dao.UserDao;
import ru.otus.api.model.AddressDataSet;
import ru.otus.api.model.PhoneDataSet;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;
import ru.otus.api.service.DbServiceUserCached;
import ru.otus.api.service.DbServiceUserImpl;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class DbServiceDemo {
  private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

  public static void main(String[] args) {
    SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
            User.class, AddressDataSet.class, PhoneDataSet.class);

    SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
    UserDao userDao = new UserDaoHibernate(sessionManager);
    DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

    Instant start = Instant.now();
    processUsers(dbServiceUser);
    Instant finish = Instant.now();
    System.out.println(String.format("Time without caching in ms: %d", Duration.between(start, finish).toMillis()));

    HwCache<Long, User> myCache = new MyCache<Long, User>(1000);
    dbServiceUser = new DbServiceUserCached(userDao, myCache);

//    HwListener<Long, User> listener =
//            (key, value, action) -> logger.info("key:{}, value:{}, action: {}", key, value, action);
//    myCache.addListener(listener);

    start = Instant.now();
    processUsers(dbServiceUser);
    finish = Instant.now();
    System.out.println(String.format("Time with caching in ms: %d", Duration.between(start, finish).toMillis()));
  }

  private static void processUsers(DBServiceUser dbServiceUser) {
    long id = 1000L;

    for (long i = 0; i < 1000L; i++) {
      User user = new User("Вася #"+i);
      id = dbServiceUser.saveUser(user);
    }

    for (long i = id - 999L; i <= id; i++) {
      Optional<User> optionalUser = dbServiceUser.getUser(i);
    }

  }

  private static void outputUserOptional(String header, Optional<User> mayBeUser) {
    System.out.println("-----------------------------------------------------------");
    System.out.println(header);
    mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
  }
}

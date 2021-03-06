package ru.otus.hibernate.dao;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.api.dao.UserDao;
import ru.otus.api.dao.UserDaoException;
import ru.otus.api.model.User;
import ru.otus.api.sessionmanager.SessionManager;
import ru.otus.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoHibernate implements UserDao {
  private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

  private final SessionManagerHibernate sessionManager;

  public UserDaoHibernate(SessionManagerHibernate sessionManager) {
    this.sessionManager = sessionManager;
  }


  @Override
  public Optional<User> findById(long id) {
    DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
    try {
      return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return Optional.empty();
  }


  @Override
  public long saveUser(User user) {
    DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
    try {
      Session hibernateSession = currentSession.getHibernateSession();
      if (user.getId() > 0) {
        hibernateSession.merge(user);
      } else {
        hibernateSession.persist(user);
      }
      return user.getId();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new UserDaoException(e);
    }
  }

  @Override
  public SessionManager getSessionManager() {
    return sessionManager;
  }

  @Override
  public Optional<User> findByLogin(String login) {
    DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
    try {
      return currentSession.getHibernateSession()
              .byNaturalId(User.class)
              .using("login", login)
              .loadOptional();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return Optional.empty();
  }

  @Override
  public List<User> getAll() {
    DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
    try {
      return (List<User>) currentSession.getHibernateSession().createCriteria(User.class).list();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return new ArrayList<>();
  }
}

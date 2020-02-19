package ru.otus.web.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;

import java.util.HashMap;
import java.util.Map;

@Service
public class DBServiceImpl implements DBService {
  private static final Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);
  private final Map<Long, String> database = new HashMap<>();
  private final DBServiceUser dbServiceUser;

  public DBServiceImpl(DBServiceUser dbServiceUser) {
    this.dbServiceUser = dbServiceUser;
  }

  public long saveUser(User user) {
    logger.info("get data for id:{}", user);
    return dbServiceUser.saveUser(user);
  }

}

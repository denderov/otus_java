package ru.otus.web.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;

import java.util.HashMap;
import java.util.List;
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

  @Override
  public String getAllUsers() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    List<User> users = dbServiceUser.getAll();
    return mapper.writeValueAsString(users);
  }

}

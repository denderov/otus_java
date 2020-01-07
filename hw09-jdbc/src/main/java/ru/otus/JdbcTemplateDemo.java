package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.model.User;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.JdbcTemplate;
import ru.otus.jdbc.JdbcTemplateException;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class JdbcTemplateDemo {
  private static Logger logger = LoggerFactory.getLogger(JdbcTemplateDemo.class);

  public static void main(String[] args) throws Exception, JdbcTemplateException {
    DataSource dataSource = new DataSourceH2();
    JdbcTemplateDemo demo = new JdbcTemplateDemo();

    demo.createTable(dataSource);

    SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
    DbExecutor<User> dbExecutor = new DbExecutor<>();
    JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>(sessionManager, dbExecutor, User.class);

    User user1 = new User(0,"User1",20);
    System.out.println(user1);
    jdbcTemplate.create(user1);
    System.out.println(user1);

    user1.setAge(user1.getAge()+1);
    jdbcTemplate.update(user1);
    System.out.println(user1);

    User user2 = jdbcTemplate.load(1);
    System.out.println(user2);

  }

  private void createTable(DataSource dataSource) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50), age int(3))")) {
      pst.executeUpdate();
    }
    System.out.println("table created");
  }
}

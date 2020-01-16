package ru.otus.api.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sq")
  @Column(name = "user_id")
  private long id;

  @Column(name = "name")
  private String name;

  @NaturalId
  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  public User(String name) {
    this.name = name;
  }

  public User(String name, String login, String password) {
    this.name = name;
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }
}

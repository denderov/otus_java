package ru.otus.api.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collection;

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

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private AddressDataSet addressDataSet;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private Collection<PhoneDataSet> phoneDataSet;

  public User(String name, AddressDataSet addressDataSet, Collection<PhoneDataSet> phoneDataSet) {
    this.name = name;
    this.addressDataSet = addressDataSet;
    this.phoneDataSet = phoneDataSet;
  }

  public User(String name, String login, AddressDataSet addressDataSet, Collection<PhoneDataSet> phoneDataSet) {
    this.name = name;
    this.login = login;
    this.addressDataSet = addressDataSet;
    this.phoneDataSet = phoneDataSet;
  }

  public User(String name) {
    this.name = name;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return "123";
  }
}

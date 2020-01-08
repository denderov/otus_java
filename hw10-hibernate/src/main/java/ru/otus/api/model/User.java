package ru.otus.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sq")
  @Column(name = "user_id")
  private long id;

  @Column(name = "name")
  private String name;

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

  public User(String name) {
    this.name = name;
  }
}

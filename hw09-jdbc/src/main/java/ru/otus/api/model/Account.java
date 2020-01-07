package ru.otus.api.model;

import java.math.BigDecimal;

public class Account {
  @Id
  private BigDecimal no;
  private String type;
  private BigDecimal rest;

  public Account() {
  }

  public Account(BigDecimal no, String type, BigDecimal rest) {
    this.no = no;
    this.type = type;
    this.rest = rest;
  }

  public BigDecimal getNo() {
    return no;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getRest() {
    return rest;
  }

  public void setRest(BigDecimal rest) {
    this.rest = rest;
  }

  @Override
  public String toString() {
    return "Account{" +
            "no=" + no +
            ", type='" + type + '\'' +
            ", rest=" + rest +
            '}';
  }
}

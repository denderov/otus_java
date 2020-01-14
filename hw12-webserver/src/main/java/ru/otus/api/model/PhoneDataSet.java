package ru.otus.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@Data
@NoArgsConstructor
public class PhoneDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_sq")
    @Column(name = "phone_id")
    private long id;

    @Column(name = "number")
    private String number;

    public PhoneDataSet(String number) {
        this.number = number;
    }
}

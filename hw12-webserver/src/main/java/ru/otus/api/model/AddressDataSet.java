package ru.otus.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class AddressDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sq")
    @Column(name = "address_id")
    private long id;

    @Column(name = "street")
    private String street;

    public AddressDataSet(String street) {
        this.street = street;
    }
}

package org.acme.hibernate.orm.panache;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.math.BigDecimal;

@Entity
@Cacheable
public class Hotel extends PanacheEntity {

    @Id
    public Long id;

    @NotNull
    @Size(max=50)
    public String name;

    public String address;
    public String city;

    @Column(length = 5)
    public String zip;
    public String country;

    public BigDecimal price;

    public Hotel() {
    }

    public Hotel(String name) {
        this.name = name;
    }

}

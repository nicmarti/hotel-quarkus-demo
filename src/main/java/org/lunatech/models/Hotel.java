package org.lunatech.models;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.math.BigDecimal;

@Entity
@Cacheable
public class Hotel extends PanacheEntity {

    public String hotelId;

    @NotNull
    @Size(max=50)
    public String name;

    public String address;

    @Column(length = 5)
    public String zip;

    @NotNull
    public String city;

    public String hotelImageURL;

    public BigDecimal price;

    @Column(length=10485760)
    public String description;

    public Hotel() {
    }

}

package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * An Hotel bookin made by a user.
 * This is inspired from JBoss Seam Hotel booking (2009...)
 *
 * @author created by N.Martignole, Lunatech, on 2019-06-05.
 */
@Entity
public class Booking extends PanacheEntity {
    @Id
    public Long id;

    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID bookingRef;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hotelId",referencedColumnName = "hotelId")
    public Hotel hotel;

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date checkinDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date checkoutDate;


    @javax.validation.constraints.Pattern(regexp = "^\\d{16}$", message = "Credit card number must be 16 digits")
    public String creditCard;


    public Booking() {
    }

    public static Booking clearCreditCard(Booking booking) {
        booking.creditCard = booking.creditCard.substring(0,3) + "**** **** ****";
        return booking;
    }

    public BigDecimal getTotal() {
        return hotel.price.multiply(new BigDecimal(getNights()));
    }

    public int getNights() {
        return (int) (checkoutDate.getTime() - checkinDate.getTime()) / 1000 / 60 / 60 / 24;
    }

    public String getDescription() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return hotel == null ? null : hotel.name +
                ", " + df.format(checkinDate) +
                " to " + df.format(checkoutDate);
    }

}

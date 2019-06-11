package org.acme.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author created by N.Martignole, Lunatech, on 2019-06-11.
 */
public class BookingRequest {
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    public BookingRequest() {
    }

    public BookingRequest(String checkin, String checkout) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate checkinDate = LocalDate.parse(checkin, formatter);
        LocalDate checkoutDate = LocalDate.parse(checkout, formatter);
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }
}

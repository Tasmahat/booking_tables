package com.project.book_tables.DBs.Patterns;

import com.project.book_tables.DBs.Booking;
import com.project.book_tables.DBs.Tables;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class UpdateFacade {

    private Booking booking;

    private Tables table;

    public UpdateFacade(Booking booking) {
        this.booking = booking;
    }

    public UpdateFacade(Tables table) {
        this.table = table;
    }

    public void updateBooking(
            Optional<String> name,
            Optional<String> phone,
            Optional<Tables> table,
            Optional<LocalDate> date,
            Optional<LocalTime> time,
            Optional<String> comment
    ) {
        if (booking != null) {
            name.ifPresent(booking::setCustomerName);
            phone.ifPresent(booking::setPhoneNumber);
            date.ifPresent(booking::setDate);
            time.ifPresent(booking::setTime);
            comment.ifPresent(booking::setComment);
            table.ifPresent(booking::setTable);
        }
    }

    public void updateTable(Optional<String> name) {
        if (table != null) {
            name.ifPresent(table::setName);
            table.notifyObservers();
        }
    }
}

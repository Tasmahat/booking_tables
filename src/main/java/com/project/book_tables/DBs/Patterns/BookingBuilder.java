package com.project.book_tables.DBs.Patterns;

import com.project.book_tables.DBs.Booking;
import com.project.book_tables.DBs.Tables;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingBuilder {

    private String customerName;

    private String phoneNumber;

    private Tables table;

    private LocalDate date;

    private LocalTime time;

    private String comment;

    public BookingBuilder() {
        super();
    }

    public BookingBuilder setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public BookingBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public BookingBuilder setTable(Tables table) {
        this.table = table;
        return this;
    }

    public BookingBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BookingBuilder setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public BookingBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Booking build() {
        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setPhoneNumber(phoneNumber);
        booking.setTable(table);
        booking.setDate(date);
        booking.setTime(time);
        booking.setComment(comment);
        return booking;
    }
}

package com.project.book_tables.DBs;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="restaurant_tables")
public class Tables {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id_t")
    private Long id;

    @Column (name = "name_t")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "table", cascade = CascadeType.REMOVE)
    private Set<Booking> bookings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    //Реализация паттерна Observer
    public void notifyObservers() {
        for (Booking booking : this.bookings) {
            booking.updateTableNameNotif(name);
        }
    }
}

package com.project.book_tables.DBs;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id_b")
    private Long id;

    @Column (name = "customer_name")
    private String customerName;

    @Column (name = "phone_number")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_t")
    private Tables table = new Tables();

    @Column (name = "date_b")
    private Date date;

    @Column (name = "time_b")
    private Time time;

    @Column (name = "comment_b")
    private String comment;

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Tables getTable() {
        return table;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

package com.project.book_tables.DBs.Controllers;

import com.project.book_tables.DBs.Booking;
import com.project.book_tables.DBs.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/all")
    public List<Booking> getAllBooking() {
        return bookingService.getAllBookings();
    }

    @PostMapping("create")
    public Booking createBooking(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam Long tableId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time,
            @RequestParam String comment
    ){
        return bookingService.createBooking(name, phone, tableId, date, time, comment);
    }

    @PostMapping("/update")
    public Booking updateBooking(
            @RequestParam Long id,
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> phone,
            @RequestParam Optional<Long> tableId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Optional<LocalDate> date,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") Optional<LocalTime> time,
            @RequestParam Optional<String> comment
    ) {
        return bookingService.updateBooking(id, name, phone, tableId, date, time, comment);
    }

    @DeleteMapping("/delete")
    public void deleteBooking(@RequestParam Long id) {
        bookingService.deleteBookingById(id);
    }
}

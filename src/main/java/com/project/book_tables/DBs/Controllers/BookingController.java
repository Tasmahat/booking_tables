package com.project.book_tables.DBs.Controllers;

import com.project.book_tables.DBs.Booking;
import com.project.book_tables.DBs.Services.BookingService;
import com.project.book_tables.DBs.Services.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final TablesService tablesService;

    @Autowired
    public BookingController(BookingService bookingService, TablesService tablesService) {
        this.bookingService = bookingService;
        this.tablesService = tablesService;
    }

    @GetMapping("/get_all_booking")
    public List<Booking> getAllBooking() {
        return bookingService.getAllBookings();
    }

    @PostMapping("save_booking")
    public void saveBooking(@RequestParam String name,
                            @RequestParam String phone,
                            @RequestParam Long table_id,
                            @RequestParam LocalDate date,
                            @RequestParam LocalTime time,
                            @RequestParam String comment
    ){
        Booking booking = new Booking();
        booking.setCustomerName(name);
        booking.setPhoneNumber(phone);
        booking.setTable(tablesService.getTableById(table_id));
        booking.setDate(date);
        booking.setTime(time);
        booking.setComment(comment);
        bookingService.createBooking(booking);
    }
}

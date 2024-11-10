package com.project.book_tables.DBs.Controllers;

import com.project.book_tables.DBs.Booking;
import com.project.book_tables.DBs.Services.BookingService;
import com.project.book_tables.DBs.Services.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("create_booking")
    public Booking createBooking(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam Long tableId,
            @RequestParam LocalDate date,
            @RequestParam LocalTime time,
            @RequestParam String comment
    ){
        Booking booking = new Booking();
        booking.setCustomerName(name);
        booking.setPhoneNumber(phone);
        booking.setTable(tablesService.getTableById(tableId));
        booking.setDate(date);
        booking.setTime(time);
        booking.setComment(comment);
        return bookingService.createBooking(booking);
    }

    @PostMapping("/update_booking")
    public Booking updateBooking(
            @RequestParam Long id,
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> phone,
            @RequestParam Optional <Long> tableId,
            @RequestParam Optional<LocalDate> date,
            @RequestParam Optional<LocalTime> time,
            @RequestParam Optional<String> comment
    ) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            System.out.println("Брони не существует");
            return null;
        }
        name.ifPresent(booking::setCustomerName);
        phone.ifPresent(booking::setPhoneNumber);
        date.ifPresent(booking::setDate);
        time.ifPresent(booking::setTime);
        comment.ifPresent(booking::setComment);

        tableId.map(tablesService::getTableById).ifPresent(booking::setTable);
        return bookingService.updateBooking(booking);
    }

    @DeleteMapping("/delete_booking")
    public void deleteBooking(@RequestParam Long id) {
        if (bookingService.getBookingById(id) == null) {
            System.out.println("Не существует брони!");
            return;
        }
        bookingService.deleteBookingById(id);
    }
}

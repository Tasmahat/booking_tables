package com.project.book_tables.DBs.Services;


import com.project.book_tables.DBs.Repositories.BookingRepository;
import com.project.book_tables.DBs.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TablesService tablesService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, TablesService tablesService) {
        this.bookingRepository = bookingRepository;
        this.tablesService = tablesService;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking createBooking(
            String name,
            String phone,
            Long tableId,
            LocalDate date,
            LocalTime time,
            String comment
    ) {
        Booking booking = new Booking();
        booking.setCustomerName(name);
        booking.setPhoneNumber(phone);
        booking.setTable(tablesService.getTableById(tableId));
        booking.setDate(date);
        booking.setTime(time);
        booking.setComment(comment);
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(
            Long id,
            Optional<String> name,
            Optional<String> phone,
            Optional <Long> tableId,
            Optional<LocalDate> date,
            Optional<LocalTime> time,
            Optional<String> comment
    ) {
        Booking booking = getBookingById(id);
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
        return bookingRepository.save(booking);
    }

    public void deleteBookingById(Long id) {
        if (getBookingById(id) == null) {
            System.out.println("Не существует брони!");
            return;
        }
        bookingRepository.deleteById(id);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}

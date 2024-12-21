package com.project.book_tables.DBs.Services;


import com.project.book_tables.DBs.Patterns.BookingBuilder;
import com.project.book_tables.DBs.Patterns.UpdateFacade;
import com.project.book_tables.DBs.Repositories.BookingRepository;
import com.project.book_tables.DBs.Booking;
import com.project.book_tables.DBs.Tables;
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
        BookingBuilder builder = new BookingBuilder();
        Booking booking = builder
                .setCustomerName(name)
                .setPhoneNumber(phone)
                .setTable(tablesService.getTableById(tableId))
                .setDate(date)
                .setTime(time)
                .setComment(comment)
                .build();
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(
            Long id,
            Optional<String> name,
            Optional<String> phone,
            Optional<Long> tableId,
            Optional<LocalDate> date,
            Optional<LocalTime> time,
            Optional<String> comment
    ) {
        Booking booking = getBookingById(id);
        if (booking == null) {
            System.out.println("Брони не существует");
            return null;
        }
        Optional<Tables> table = tableId.map(tablesService::getTableById);

        UpdateFacade updateFacade = new UpdateFacade(booking);
        updateFacade.updateBooking(name, phone, table, date, time, comment);
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

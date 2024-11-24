package com.project.book_tables;

import com.project.book_tables.DBs.Booking;
import com.project.book_tables.DBs.Repositories.BookingRepository;
import com.project.book_tables.DBs.Services.BookingService;
import com.project.book_tables.DBs.Services.TablesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;


public class BookingServiceUnitTests {
    private BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
    private TablesService tablesService = Mockito.mock(TablesService.class);
    private BookingService bookingService = new BookingService(bookingRepository,tablesService);

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Test
    void testForGetBookingShouldReturnList(){
        Mockito.when(bookingRepository.findAll()).thenReturn(List.of());
        assertEquals("Должен вернуть список!", List.of(), bookingService.getAllBookings());
    }

    @Test
    void testForCreateBookingShouldReturnBooking() {
        Booking booking = new Booking();
        Date date = new Date();
        Time time = new Time(1);

        Mockito.when(bookingRepository.save(Mockito.any(Booking.class))).thenReturn(booking);

        assertEquals("Должен вернуть объект Booking", booking, bookingService.createBooking(
                "",
                "",
                1L,
                date,
                time,
                ""
                )
        );
    }

    @Test
    void testForUpdateBookingShouldReturnBooking() {
        Booking booking = new Booking();

        Mockito.doReturn(Optional.ofNullable(booking)).when(bookingRepository).findById(1L);
        Mockito.when(bookingRepository.save(booking)).thenReturn(booking);

        assertEquals("Должен вернуть объект Booking!", booking, bookingService.updateBooking(
                1L,
                Optional.of(""),
                Optional.of(""),
                Optional.of(1L),
                Optional.of(new Date()),
                Optional.of(new Time(1L)),
                Optional.of("")
                )
        );
    }

    @Test
    void testForUpdateBookingShouldReturnNullAndDoesntExistsMessage() {
        Booking booking = new Booking();
        System.setOut(new PrintStream(output));

        Mockito.doReturn(Optional.ofNullable(null)).when(bookingRepository).findById(1L);

        assertEquals("Должен вернуть null!", null, bookingService.updateBooking(
                1L,
                Optional.of(""),
                Optional.of(""),
                Optional.of(1L),
                Optional.of(new Date()),
                Optional.of(new Time(1L)),
                Optional.of("")
                )
        );
        assertEquals("Должен вернуть сообщение!", "Брони не существует", output.toString().trim());

        System.setOut(null);
    }

    @Test
    void testForDeleteBookingShouldntPrintAnything() {
        Booking booking = new Booking();
        System.setOut(new PrintStream(output));

        Mockito.doReturn(Optional.ofNullable(booking)).when(bookingRepository).findById(1L);

        bookingService.deleteBookingById(1L);

        assertEquals("Должен вернуть ничего!", "", output.toString().trim());

        System.setOut(null);
    }

    @Test
    void testForDeleteBookingShouldPrintDoesntExistMessage() {
        Booking booking = new Booking();
        System.setOut(new PrintStream(output));

        Mockito.doReturn(Optional.ofNullable(null)).when(bookingRepository).findById(1L);

        bookingService.deleteBookingById(1L);

        assertEquals("Должен вернуть сообщение!", "Не существует брони!", output.toString().trim());

        System.setOut(null);
    }
}
package com.project.book_tables.DBs.Repositories;

import com.project.book_tables.DBs.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

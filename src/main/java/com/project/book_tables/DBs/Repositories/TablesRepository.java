package com.project.book_tables.DBs.Repositories;

import com.project.book_tables.DBs.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TablesRepository extends JpaRepository<Tables, Long> {
}

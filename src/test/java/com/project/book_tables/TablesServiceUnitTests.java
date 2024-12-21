package com.project.book_tables;

import com.project.book_tables.DBs.Repositories.TablesRepository;
import com.project.book_tables.DBs.Services.TablesService;
import com.project.book_tables.DBs.Tables;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class TablesServiceUnitTests {

    private TablesRepository tablesRepository = Mockito.mock(TablesRepository.class);
    private TablesService tablesService = new TablesService(tablesRepository);

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test
    void testForGetAllTablesShouldReturnList() {
        Mockito.when(tablesRepository.findAll()).thenReturn(List.of());

        assertEquals("Должен вернуться список", List.of(), tablesService.getAllTables());
    }

    @Test
    void testForCreateTablesShouldReturnTable() {
        Tables table = new Tables();

        Mockito.when(tablesRepository.save(Mockito.any(Tables.class))).thenReturn(table);

        assertEquals("Должен вернуться объект Tables", table, tablesService.createTable(""));
    }

    @Test
    void testForDeleteTablesShouldntPrintAnything() {
        Tables table = new Tables();
        System.setOut(new PrintStream(output));

        Mockito.doReturn(Optional.ofNullable(table)).when(tablesRepository).findById(1L);

        tablesService.deleteTableById(1L);

        assertEquals("Должен вернуть ничего!", "", output.toString().trim());

        System.setOut(null);
    }

    @Test
    void testForDeleteTablesShouldPrintDoesntExistMessage() {
        System.setOut(new PrintStream(output));

        Mockito.doReturn(Optional.ofNullable(null)).when(tablesRepository).findById(1L);

        tablesService.deleteTableById(1L);

        assertEquals("Должен вернуть сообщение!", "Не существует столика!", output.toString().trim());

        System.setOut(null);
    }

    @Test
    void testForUpdateTablesShouldReturnTable() {
        Tables table = new Tables();

        Mockito.when(tablesRepository.save(Mockito.any(Tables.class))).thenReturn(table);
        Mockito.doReturn(Optional.ofNullable(table)).when(tablesRepository).findById(1L);

        assertEquals("Дожен вернуть объект Tables", table, tablesService.updateTable(1L, ""));
    }

    @Test
    void testForUpdateTablesShouldReturnNullAndDoesntExistsMessage() {
        Tables table = new Tables();
        System.setOut(new PrintStream(output));

        Mockito.when(tablesRepository.save(Mockito.any(Tables.class))).thenReturn(table);
        Mockito.doReturn(Optional.ofNullable(null)).when(tablesRepository).findById(1L);

        assertEquals("Должен вернуть null", null, tablesService.updateTable(1L, ""));
        assertEquals("Должен вернуть сообщение!", "Не существует столика!", output.toString().trim());

        System.setOut(null);
    }
}
package com.project.book_tables.DBs.Services;


import com.project.book_tables.DBs.Patterns.UpdateFacade;
import com.project.book_tables.DBs.Repositories.TablesRepository;
import com.project.book_tables.DBs.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TablesService {

    private final TablesRepository tablesRepository;

    @Autowired
    public TablesService(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    public List<Tables> getAllTables() {
        return tablesRepository.findAll();
    }

    public Tables createTable(String name) {
        Tables table = new Tables();
        table.setName(name);
        return tablesRepository.save(table);
    }

    public void deleteTableById(Long id) {
        if (getTableById(id) == null) {
            System.out.println("Не существует столика!");
            return;
        }
        tablesRepository.deleteById(id);
    }

    public Tables updateTable(Long id, String name) {
        Tables table = getTableById(id);
        if (table == null) {
            System.out.println("Не существует столика!");
            return null;
        }
        UpdateFacade updateFacade = new UpdateFacade(table);
        updateFacade.updateTable(Optional.of(name));
        return tablesRepository.save(table);
    }

    public Tables getTableById(Long id) {
        return tablesRepository.findById(id).orElse(null);
    }
}

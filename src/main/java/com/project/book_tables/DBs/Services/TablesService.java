package com.project.book_tables.DBs.Services;


import com.project.book_tables.DBs.Repositories.TablesRepository;
import com.project.book_tables.DBs.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Tables createTable(Tables table) {
        return tablesRepository.save(table);
    }

    public Tables updateTable(Long id, String name) {
        Tables table;
        table = tablesRepository.findById(id).orElse(null);
        try {
            table.setName(name);
        } catch (Exception e) {
            System.out.println("Имя столика не поменялось!");
        }
        return tablesRepository.save(table);
    }

    public void deleteTableById(Long id) {
        tablesRepository.deleteById(id);
    }

    public Tables getTableById(Long id) {
        return tablesRepository.findById(id).orElse(null);
    }
}

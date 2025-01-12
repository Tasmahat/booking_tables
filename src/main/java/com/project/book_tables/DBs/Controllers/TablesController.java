package com.project.book_tables.DBs.Controllers;

import com.project.book_tables.DBs.Services.TablesService;
import com.project.book_tables.DBs.Tables;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tables")
public class TablesController {

    private final TablesService tablesService;

    @Autowired
    public TablesController(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    @GetMapping("/all")
    public List<Tables> getAllTables() {
        return tablesService.getAllTables();
    }

    @PostMapping("/create")
    public Tables createTable(@RequestParam String name) {
        return tablesService.createTable(name);
    }

    @DeleteMapping("/delete")
    public void deleteTable(@RequestParam Long id) {
        tablesService.deleteTableById(id);
    }

    //id кого меняем, name что меняем
    @PostMapping("/update")
    public Tables updateTable(@RequestParam Long id, @RequestParam String name) {
        return tablesService.updateTable(id, name);
    }
}

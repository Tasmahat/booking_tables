package com.project.book_tables.DBs.Controllers;

import com.project.book_tables.DBs.Services.TablesService;
import com.project.book_tables.DBs.Tables;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TablesController {

    private final TablesService tablesService;

    @Autowired
    public TablesController(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    @GetMapping("/get_all_tables")
    public List<Tables> getAllTables() {
        return tablesService.getAllTables();
    }

    @PostMapping("/save_table")
    public void createTable(@RequestParam String name) {
        Tables table = new Tables();
        table.setName(name);
        tablesService.createTable(table);
    }

    @DeleteMapping("/delete_table")
    public void deleteTable(@RequestParam Long id) {
        tablesService.deleteTableById(id);
    }

    //id кого меняем, name что меняем
    @PostMapping("/update_table")
    public void updateTable(@RequestParam Long id, @RequestParam String name)
    {
        tablesService.updateTable(id, name);
    }
}

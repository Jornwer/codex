package com.jornwer.codex.controller;

import com.jornwer.codex.dto.ItemDto;
import com.jornwer.codex.exception.DuplicateException;
import com.jornwer.codex.model.Item;
import com.jornwer.codex.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Item addItem(@RequestBody ItemDto item) {
        return itemService.add(item);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Item> findAll() {
        return itemService.findAll();
    }
}

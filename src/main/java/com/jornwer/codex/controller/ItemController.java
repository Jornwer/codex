package com.jornwer.codex.controller;

import com.jornwer.codex.dto.ItemDto;
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

    @PostMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteItem(@PathVariable long id) {
        itemService.delete(id);
    }

    @PostMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Item editItem(@PathVariable long id,
                         @RequestBody ItemDto item,
                         @RequestParam(required = false) boolean force) {
        return itemService.edit(id, item, force);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Item> findAll(@RequestParam(required = false) String description,
                              @RequestParam(required = false) List<String> tags) {
        return itemService.findAll(description, tags);
    }
}

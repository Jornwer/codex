package com.jornwer.codex.controller;

import com.jornwer.codex.dto.ItemDto;
import com.jornwer.codex.model.Item;
import com.jornwer.codex.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Add item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item added"),
            @ApiResponse(code = 400, message = "Name not unique"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public Item addItem(@RequestBody ItemDto item) {
        return itemService.add(item);
    }

    @PostMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Delete item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item removed"),
            @ApiResponse(code = 400, message = "Item not found"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public void deleteItem(@PathVariable long id) {
        itemService.delete(id);
    }

    @PostMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Edit item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item edited"),
            @ApiResponse(code = 400, message = "Item not found"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public Item editItem(@PathVariable long id,
                         @RequestBody ItemDto item,
                         @RequestParam(required = false) boolean force) {
        return itemService.edit(id, item, force);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Find items")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Items found"),
            @ApiResponse(code = 400, message = "Error acquired"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public List<Item> findAll(@RequestParam(required = false) String description,
                              @RequestParam(required = false) List<String> tags) {
        return itemService.findAll(description, tags);
    }
}

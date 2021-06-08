package com.jornwer.codex.service;

import com.jornwer.codex.dto.ItemDto;
import com.jornwer.codex.model.Item;

import java.util.List;

public interface ItemService {
    Item add(ItemDto item);
    Item findById(Long id);
    void delete(long id);
    Item edit(long id, ItemDto item, boolean force);
    List<Item> findAll(String description, List<String> tags);
}

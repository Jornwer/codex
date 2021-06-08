package com.jornwer.codex.service;

import com.jornwer.codex.dto.ItemDto;
import com.jornwer.codex.exception.DuplicateException;
import com.jornwer.codex.model.Item;

import java.util.List;

public interface ItemService {
    Item add(ItemDto item);
    List<Item> findAll();
    Item findById(Long id);
}

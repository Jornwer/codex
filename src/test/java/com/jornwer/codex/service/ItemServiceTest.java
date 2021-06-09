package com.jornwer.codex.service;

import com.jornwer.codex.dto.ItemDto;
import com.jornwer.codex.exception.DuplicateException;
import com.jornwer.codex.exception.NotFoundException;
import com.jornwer.codex.model.Item;
import com.jornwer.codex.model.Tag;
import com.jornwer.codex.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ItemRepository itemRepository;

    private Item item;

    @BeforeEach
    public void setup() {
        item = Item.builder()
                .name(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .tags(tagService.mapTags(Set.of(UUID.randomUUID().toString(), UUID.randomUUID().toString())))
                .build();
        itemRepository.save(item);
    }

    @Test
    public void shouldThrowExceptionIfItemNameNotUnique() {
        assertThrows(DuplicateException.class,
                () -> itemService.add(new ItemDto(item.getName(), item.getDescription(), Set.of("1", "2"))));
    }

    @Test
    public void shouldNotThrowExceptionIfItemNameUnique() {
        itemService.add(new ItemDto(UUID.randomUUID().toString(), item.getDescription(), Set.of("1", "2")));
    }

    @Test
    public void shouldFindItemById() {
        Item itemById = itemService.findById(item.getId());
        assertEquals(itemById, item);
    }

    @Test
    public void shouldDeleteItem() {
        itemService.delete(item.getId());
        assertThrows(NotFoundException.class, () -> itemService.findById(item.getId()));
    }

    @Test
    public void shouldFindItems() {
        assertNotEquals(0, itemService.findAll("", null).size());
        assertEquals(1, itemService.findAll(item.getDescription(), null).size());
        assertEquals(1, itemService.findAll("",
                item.getTags().stream().map(Tag::getName).collect(Collectors.toList())).size());
        assertEquals(1, itemService.findAll(item.getDescription(),
                item.getTags().stream().map(Tag::getName).collect(Collectors.toList())).size());
        assertEquals(0, itemService.findAll(UUID.randomUUID().toString(), null).size());
    }
}

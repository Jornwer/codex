package com.jornwer.codex.service.impl;

import com.jornwer.codex.dto.ItemDto;
import com.jornwer.codex.exception.DuplicateException;
import com.jornwer.codex.model.Item;
import com.jornwer.codex.model.Tag;
import com.jornwer.codex.repository.ItemRepository;
import com.jornwer.codex.repository.TagRepository;
import com.jornwer.codex.service.ItemService;
import com.jornwer.codex.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final TagService tagService;

    @Override
    public Item add(ItemDto dto) throws DuplicateException {
        if (itemRepository.existsByName(dto.getName())) {
            throw new DuplicateException("Item name should be unique");
        }

        Item item = Item.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .tags(tagService.mapTags(dto.getTags()))
                .build();
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}

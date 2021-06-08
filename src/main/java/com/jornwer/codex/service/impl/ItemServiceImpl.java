package com.jornwer.codex.service.impl;

import com.jornwer.codex.dto.ItemDto;
import com.jornwer.codex.exception.DuplicateException;
import com.jornwer.codex.exception.NotFoundException;
import com.jornwer.codex.model.Cart;
import com.jornwer.codex.model.Item;
import com.jornwer.codex.repository.ItemRepository;
import com.jornwer.codex.service.CartService;
import com.jornwer.codex.service.ItemService;
import com.jornwer.codex.service.MailService;
import com.jornwer.codex.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final TagService tagService;
    private final CartService cartService;
    private final MailService mailService;

    public ItemServiceImpl(ItemRepository itemRepository,
                           TagService tagService,
                           @Lazy CartService cartService,
                           MailService mailService) {
        this.itemRepository = itemRepository;
        this.tagService = tagService;
        this.cartService = cartService;
        this.mailService = mailService;
    }

    @Override
    public Item add(ItemDto dto) {
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

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item with id =  " + id + " not found"));
    }

    @Override
    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item edit(long id, ItemDto dto, boolean force) {
        Item item = findById(id);
        List<Cart> carts = cartService.findCartsContainingItem(item);

        if (carts.size() > 0) {
            if (force) {
                alertAllUser(dto, carts, item);
                return saveItemFromDto(item, dto);
            }
            throw new RuntimeException("This item in someone's cart. If you want to edit this item use ?force=true");
        }
        return saveItemFromDto(item, dto);
    }

    private Item saveItemFromDto(Item item, ItemDto dto) {
        item.setDescription(dto.getDescription());
        item.setName(dto.getName());
        item.setTags(tagService.mapTags(dto.getTags()));

        return itemRepository.save(item);
    }

    private void alertAllUser(ItemDto dto, List<Cart> carts, Item item) {
        StringBuilder mailText = new StringBuilder("Item with name ");
        mailText.append(item.getName()).append(" changed to:\n");
        mailText.append(dto.getName()).append('\n');
        mailText.append(dto.getDescription());
        dto.getTags().forEach(tag -> mailText.append('\n').append(tag));

        carts.stream()
                .map(cart -> cart.getUser().getEmail())
                .distinct()
                .forEach(user -> mailService.sendMailTo(user, mailText.toString()));
    }
}

package com.jornwer.codex.service.impl;

import com.jornwer.codex.model.Cart;
import com.jornwer.codex.model.Item;
import com.jornwer.codex.model.User;
import com.jornwer.codex.repository.CartRepository;
import com.jornwer.codex.service.CartService;
import com.jornwer.codex.service.ItemService;
import com.jornwer.codex.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ItemService itemService;

    @Override
    public Cart addToCart(int id) {
        User user = userService.getCurrentUser();
        Optional<Cart> optionalCart = cartRepository.findByUser(user);
        Item item = itemService.findById((long) id);
        Cart cart;

        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
            if (!cart.getItems().contains(item)) {
                cart.getItems().add(item);
            }
        } else {
            cart = new Cart();
            cart.setItems(new ArrayList<>(List.of(item)));
            cart.setUser(user);
        }

        return cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(int id) {
        User user = userService.getCurrentUser();
        Optional<Cart> optionalCart = cartRepository.findByUser(user);

        optionalCart.ifPresent(cart -> {
            if (cart.getItems().size() == 1) {
                cartRepository.delete(cart);
            } else {
                cart.getItems().removeIf(item -> item.getId() == id);
                cartRepository.save(cart);
            }
        });
    }

    @Override
    public Cart viewCart() {
        User user = userService.getCurrentUser();
        Optional<Cart> optionalCart = cartRepository.findByUser(user);

        return optionalCart.orElse(null);
    }
}

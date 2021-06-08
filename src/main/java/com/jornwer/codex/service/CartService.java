package com.jornwer.codex.service;

import com.jornwer.codex.model.Cart;
import com.jornwer.codex.model.Item;

import java.util.List;

public interface CartService {
    Cart addToCart(int id);
    void removeFromCart(int id);
    Cart viewCart();
    void buyItems();
    List<Cart> findCartsContainingItem(Item item);
}

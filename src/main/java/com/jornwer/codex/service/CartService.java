package com.jornwer.codex.service;

import com.jornwer.codex.model.Cart;

public interface CartService {
    Cart addToCart(int id);
    void removeFromCart(int id);
    Cart viewCart();
    void buyItems();
}

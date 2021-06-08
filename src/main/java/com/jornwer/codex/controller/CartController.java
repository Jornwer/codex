package com.jornwer.codex.controller;

import com.jornwer.codex.model.Cart;
import com.jornwer.codex.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add/{id}")
    @PreAuthorize("isAuthenticated()")
    public Cart addToCart(@PathVariable int id) {
        return cartService.addToCart(id);
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("isAuthenticated()")
    public void removeFromCart(@PathVariable int id) {
        cartService.removeFromCart(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Cart viewCart() {
        return cartService.viewCart();
    }
}

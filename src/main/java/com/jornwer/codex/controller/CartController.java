package com.jornwer.codex.controller;

import com.jornwer.codex.model.Cart;
import com.jornwer.codex.service.CartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Add item to cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item added to cart"),
            @ApiResponse(code = 400, message = "Item not found"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public Cart addToCart(@PathVariable int id) {
        return cartService.addToCart(id);
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Remove item to cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item removed from cart"),
            @ApiResponse(code = 400, message = "Item not found"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public void removeFromCart(@PathVariable int id) {
        cartService.removeFromCart(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "View cart of authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cart shown"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public Cart viewCart() {
        return cartService.viewCart();
    }

    @PostMapping("/buy")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Buy item in cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item bought"),
            @ApiResponse(code = 403, message = "Unauthorized access")
    })
    public void buyItems() {
        cartService.buyItems();
    }
}

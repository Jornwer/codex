package com.jornwer.codex.repository;

import com.jornwer.codex.model.Cart;
import com.jornwer.codex.model.Item;
import com.jornwer.codex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
    List<Cart> findByItemsContaining(Item item);
}

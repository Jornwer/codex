package com.jornwer.codex.repository;

import com.jornwer.codex.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

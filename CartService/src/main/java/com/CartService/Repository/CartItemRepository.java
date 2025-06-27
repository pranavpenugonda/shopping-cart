package com.CartService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CartService.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}


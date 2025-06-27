package com.CartService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CartService.model.Cart;
import com.CartService.model.CartItem;
import com.CartService.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItem(@PathVariable Long userId, @RequestBody CartItem item) throws Exception {
        return ResponseEntity.ok(cartService.addItemToCart(userId, item));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{userId}/remove/{itemId}")
    public ResponseEntity<Cart> removeItem(@PathVariable Long userId, @PathVariable Long itemId) throws Exception {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, itemId));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) throws Exception {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}

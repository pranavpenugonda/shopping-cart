package com.CartService.service;

import com.CartService.exception.ProductNotFoundException;
import com.CartService.model.Cart;
import com.CartService.model.CartItem;

public interface CartService {
    Cart getCartByUserId(Long userId) throws Exception;
    Cart addItemToCart(Long userId, CartItem item) throws Exception;
    Cart removeItemFromCart(Long userId, Long itemId) throws Exception;
    void clearCart(Long userId) throws Exception;
}


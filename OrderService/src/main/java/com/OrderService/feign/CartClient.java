package com.OrderService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.OrderService.dto.CartDto;

@FeignClient(name = "CartService")
public interface CartClient {
    @GetMapping("/api/cart/{userId}")
    CartDto getCartByUserId(@PathVariable Long userId);
}


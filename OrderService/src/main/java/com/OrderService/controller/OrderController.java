package com.OrderService.controller;

import com.OrderService.exception.CartEmptyException;
import com.OrderService.exception.OrderNotFoundException;
import com.OrderService.model.Order;
import com.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId) throws CartEmptyException {
        return new ResponseEntity<>(orderService.placeOrder(userId), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>>  getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long orderId, @RequestParam String status) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}

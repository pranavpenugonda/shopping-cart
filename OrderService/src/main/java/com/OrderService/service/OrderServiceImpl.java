package com.OrderService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderService.dto.CartDto;
import com.OrderService.dto.CartItemDto;
import com.OrderService.exception.CartEmptyException;
import com.OrderService.exception.OrderNotFoundException;
import com.OrderService.feign.CartClient;
import com.OrderService.feign.ProductClient;
import com.OrderService.model.Order;
import com.OrderService.model.OrderItem;
import com.OrderService.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public Order placeOrder(Long userId) throws CartEmptyException {
        CartDto cart = cartClient.getCartByUserId(userId);

        if (cart == null || cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty. Cannot place order.");
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemDto cartItem : cart.getItems()) {
            productClient.reduceStock(cartItem.getProductId(), cartItem.getQuantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) throws OrderNotFoundException {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}


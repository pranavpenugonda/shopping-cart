package com.OrderService.service;

import java.util.List;

import com.OrderService.exception.CartEmptyException;
import com.OrderService.exception.OrderNotFoundException;
import com.OrderService.model.Order;

public interface OrderService {

	Order placeOrder(Long userId) throws CartEmptyException;

	Order getOrderById(Long orderId) throws OrderNotFoundException;

	List<Order> getOrdersByUserId(Long userId);

	Order updateOrderStatus(Long orderId, String status) throws OrderNotFoundException;

}

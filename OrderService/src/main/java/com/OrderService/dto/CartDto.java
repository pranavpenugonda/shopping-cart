package com.OrderService.dto;

import java.util.List;

public class CartDto {
    private Long userId;
    private List<CartItemDto> items;
	public CartDto() {
		super();
	}
	public CartDto(Long userId, List<CartItemDto> items) {
		super();
		this.userId = userId;
		this.items = items;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<CartItemDto> getItems() {
		return items;
	}
	public void setItems(List<CartItemDto> items) {
		this.items = items;
	}

    
}


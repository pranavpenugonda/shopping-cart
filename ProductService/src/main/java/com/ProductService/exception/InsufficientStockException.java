package com.ProductService.exception;

public class InsufficientStockException extends Exception {
	public InsufficientStockException(String msg) {
		super(msg);
	}
}

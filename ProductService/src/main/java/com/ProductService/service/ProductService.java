package com.ProductService.service;

import java.util.List;

import com.ProductService.Model.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
	void reduceStock(Long id, int quantity) throws Exception;
}


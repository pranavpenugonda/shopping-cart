package com.ProductService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProductService.Model.Product;
import com.ProductService.Repository.ProductRepository;
import com.ProductService.exception.InsufficientStockException;
import com.ProductService.exception.ProductNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                		("Product not found with id " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        product.setCategory(updatedProduct.getCategory());
        return productRepository.save(product);
    }
    
    public void reduceStock(Long productId, int quantity) throws Exception {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Not enough stock");
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}


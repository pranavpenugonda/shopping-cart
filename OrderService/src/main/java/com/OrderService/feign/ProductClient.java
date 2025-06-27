package com.OrderService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ProductService")
public interface ProductClient {
    @PutMapping("/api/products/{productId}/reduce-stock")
    void reduceStock(@PathVariable Long productId, @RequestParam int quantity);
}


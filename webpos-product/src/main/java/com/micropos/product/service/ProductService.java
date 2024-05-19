package com.micropos.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micropos.product.model.Product;
import com.micropos.product.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElse(null);
    }

}

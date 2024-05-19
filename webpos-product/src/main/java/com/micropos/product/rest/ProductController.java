package com.micropos.product.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micropos.api.ProductsApi;
import com.micropos.dto.ProductDto;
import com.micropos.product.mapper.ProductMapper;
import com.micropos.product.model.Product;
import com.micropos.product.service.ProductService;

@RestController
@RequestMapping("api")
public class ProductController implements ProductsApi {
    
    private final ProductService productService;

    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<List<ProductDto>> listProducts(String name) {

        Collection<Product> products = new ArrayList<>();

        if (name != null) {
            products.add(productService.getProductByName(name));
        } else {
            products = productService.getProducts();
        }

        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.toProductDtos(products));

    }

    @Override
    public ResponseEntity<ProductDto> showProductById(String productId) {

        Product product = productService.getProduct(productId);

        if (product != null) {
            return ResponseEntity.ok(productMapper.toProductDto(product));
        }

        return ResponseEntity.notFound().build();

    }

}

package com.micropos.product.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import com.micropos.dto.ProductDto;
import com.micropos.product.model.Product;

@Mapper
public interface ProductMapper {

    List<ProductDto> toProductDtos(Collection<Product> products);

    List<Product> toProducts(Collection<ProductDto> productDtos);

    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);
    
}

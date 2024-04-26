package com.example.art.marrer;

import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Product;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProductMapper {
    public Product toProductEntity(ProductDto productDto) {

        Product product =  Product.builder()
                .designation(productDto.getDesignation())
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .level(productDto.getLevel())
                .assemblyUnits(productDto.getAssemblyUnits())
                .build();

        if (productDto.getId() != null) product.setId(productDto.getId());

        return product;
    }

    public ProductDto toUserEntity(Product product) {

        return ProductDto.builder()
                .id(product.getId())
                .designation(product.getDesignation())
                .name(product.getName())
                .quantity(product.getQuantity())
                .level(product.getLevel())
                .assemblyUnits(product.getAssemblyUnits())
                .build();
    }
}

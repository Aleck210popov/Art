package com.example.art.service;

import com.example.art.controller.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    ProductDto getByDesignationAndVersionDate(String designation,int versionDate);
    void deleteProduct(long id);

    ProductDto updateProduct(long id, ProductDto productDto);
}

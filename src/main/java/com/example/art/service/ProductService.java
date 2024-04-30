package com.example.art.service;

import com.example.art.controller.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    List<ProductDto> getAll();
    ProductDto getById(long id);
    List<ProductDto> getByDesignation(String designation);
    ProductDto getByDesignationAndVersionDate(String designation,int versionDate);
    String[][] getForm(String designation,int versionDate);

    void deleteProduct(long id);

    ProductDto updateProduct(long id, ProductDto productDto);
}

package com.example.art.service;

import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    AssemblyUnit saveAssemblyUnit(AssemblyUnit assemblyUnit);
    Part savePart(Part part);
    List<Product> getAll();
    Product getById(long id);
}

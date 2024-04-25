package com.example.art.service;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;

public interface ProductService {
    void saveProduct(Product product);
    void saveAssemblyUnit(AssemblyUnit assemblyUnit);
    void savePart(Part part);
}

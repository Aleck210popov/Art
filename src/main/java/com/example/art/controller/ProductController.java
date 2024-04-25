package com.example.art.controller;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @PostMapping("/assemblyUnits")
    public void addAssemblyUnit(@RequestBody AssemblyUnit assemblyUnit) {
        productService.saveAssemblyUnit(assemblyUnit);
    }

    @PostMapping("/parts")
    public void addPart(@RequestBody Part part) {
        productService.savePart(part);
    }
}

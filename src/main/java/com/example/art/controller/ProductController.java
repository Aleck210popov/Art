package com.example.art.controller;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/assemblyUnits")
    public AssemblyUnit addAssemblyUnit(@RequestBody AssemblyUnit assemblyUnit) {
        return productService.saveAssemblyUnit(assemblyUnit);
    }

    @PostMapping("/parts")
    public Part addPart(@RequestBody Part part) {
        return productService.savePart(part);
    }
    @GetMapping("/user")
    public List<Product> getAllUsers() {
        return productService.getAll();
    }
    @GetMapping("/user/{id}")
    public Product getById(@PathVariable long id) {
        return productService.getById(id);
    }
}

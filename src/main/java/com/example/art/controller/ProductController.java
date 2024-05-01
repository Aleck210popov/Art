package com.example.art.controller;

import com.example.art.controller.dto.ProductDto;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;



    @PostMapping()
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        System.out.println("эоэоэоэ");
        return productService.saveProduct(productDto);
    }

    @PutMapping("/id/{id}")
    public ProductDto updateProduct (@PathVariable long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/id/{id}")
    public void deleteProduct (@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/designation/{designation}/version/{versionDate}")
    public ProductDto getByDesignationAndVersionDate(@PathVariable String designation, @PathVariable int versionDate) {
        return productService.getByDesignationAndVersionDate(designation, versionDate);
    }

}

package com.example.art.controller;

import com.example.art.controller.dto.ProductDto;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;


    @DeleteMapping("/products/id/{id}")
    public void deleteProduct (@PathVariable long id) {
        productService.deleteProduct(id);
    }
    @PostMapping("/products")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }
    @GetMapping("/products/designation/{designation}/version/{versionDate}")
    public ProductDto getByDesignationAndVersionDate(@PathVariable String designation, @PathVariable int versionDate) {
        return productService.getByDesignationAndVersionDate(designation, versionDate);
    }
    @PutMapping("/products/{id}")
    public ProductDto update (@PathVariable long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }



//    @GetMapping("/products")
//    public List<ProductDto> getAllUsers() {
//        return productService.getAll();
//    }
//    @GetMapping("/products/id/{id}")
//    public ProductDto getById(@PathVariable long id) {
//        return productService.getById(id);
//    }
//    @GetMapping("/products/designation/{designation}")
//    public List<ProductDto> getByDesignation(@PathVariable String designation) {
//        return productService.getByDesignation(designation);
//    }

//    @GetMapping("/products/form/designation/{designation}/version/{versionDate}")
//    public String[][] getForm(@PathVariable String designation, @PathVariable int versionDate) {
//        return productService.getForm(designation, versionDate);
//    }


}

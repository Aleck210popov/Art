package com.example.art.service.impl;

import com.example.art.controller.dto.AssemblyUnitDto;
import com.example.art.controller.dto.PartDto;
import com.example.art.exception.ProductAlreadyExistsException;
import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.exception.ProductNotFoundException;
import com.example.art.mapper.ProductMapper;
import com.example.art.repository.AssemblyUnitRepository;
import com.example.art.repository.PartRepository;
import com.example.art.repository.ProductRepository;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        if (productRepository.findByDesignation(productDto.getDesignation()).isPresent())
            throw new ProductAlreadyExistsException("Product already exists");

        Product productSave = productRepository.save(ProductMapper.toProductEntity(productDto));
        return ProductMapper.toProductDto(productSave);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(ProductMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) throw new ProductNotFoundException("Product with ID " + id + " not found");
        return ProductMapper.toProductDto(product.get());
    }

    @Override
    public ProductDto getByDesignation(String designation) {
        Optional<Product> product = productRepository.findByDesignation(designation);
        if(product.isEmpty()) throw new ProductNotFoundException("Product with designation " + designation + " not found");
        return ProductMapper.toProductDto(product.get());
    }
}

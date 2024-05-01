package com.example.art.service.impl;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.exception.ProductAlreadyExistsException;
import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.Product;
import com.example.art.exception.ProductFieldException;
import com.example.art.exception.ProductNotFoundException;
import com.example.art.mapper.ProductMapper;
import com.example.art.repository.ProductRepository;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        if (productRepository.findByDesignation(productDto.getDesignation()).isPresent() &&
                productRepository.findByVersionDate(productDto.getVersionDate()).isPresent()) {
            throw new ProductAlreadyExistsException("Product with the current version date already exists");
        }

        Product productSave = productRepository.save(ProductMapper.toProductEntity(productDto));
        return ProductMapper.toProductDto(productSave);
    }

    @Override
    public ProductDto getByDesignationAndVersionDate(String designation, int versionDate) {
        Optional<Product> productOptional = productRepository.findByDesignation(designation);
        if(productOptional.isEmpty())
            throw new ProductNotFoundException("Product with designation " + designation + " not found");
        List<Product> productList = productRepository.findAllByDesignation(designation);
        productList.sort(Comparator.comparingInt(Product::getVersionDate));

        Product selectedProduct = getProduct(designation, versionDate, productList);

        return ProductMapper.toProductDto(selectedProduct);
    }
    private Product getProduct(String designation, int versionDate, List<Product> productList) {
        Product selectedProduct = null;
        for (Product product : productList) {
            if (product.getVersionDate() <= versionDate) {
                selectedProduct = product;
            } else {
                break;
            }
        }

        if (selectedProduct == null)
            throw new ProductNotFoundException("Product with designation " + designation +
                    " and versionDate less or equal to " + versionDate + " not found");
        return selectedProduct;
    }



    @Override
    public void deleteProduct(long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        productRepository.delete(productOptional.get());
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
            throw new ProductNotFoundException("Product with ID " + id + " not found");


        Product productClient = ProductMapper.toProductEntity(productDto);
        if (productClient.getDesignation() == null || productClient.getName() == null) {
            throw new ProductFieldException("Error in product field");
        }
        Product existingProduct = productOptional.get();
        Product updatedProduct = ProductMapper.toProductEntity(productDto);
        // TODO : нужно проверить id с клиента
        updatedProduct.setId(existingProduct.getId());

        existingProduct.setDesignation(updatedProduct.getDesignation());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setLevel(updatedProduct.getLevel());
        existingProduct.setVersionDate(updatedProduct.getVersionDate());
        existingProduct.setAssembliesUnits(updatedProduct.getAssembliesUnits());

        Product savedProduct = productRepository.save(existingProduct);

        return ProductMapper.toProductDto(savedProduct);
    }









}

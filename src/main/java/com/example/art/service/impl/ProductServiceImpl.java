package com.example.art.service.impl;

import com.example.art.component.exception.ProductAlreadyExistsException;
import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.marrer.ProductMapper;
import com.example.art.repository.AssemblyUnitRepository;
import com.example.art.repository.PartRepository;
import com.example.art.repository.ProductRepository;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AssemblyUnitRepository assemblyUnitRepository;
    private final PartRepository partRepository;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        if (productRepository.findByDesignation(productDto.getDesignation()).isPresent())
            throw new ProductAlreadyExistsException("Product already exists");

        Product product = ProductMapper.toProductEntity(productDto);
        Product productSave = productRepository.save(product);
        return ProductMapper.toProductDto(productSave);
    }

    @Override
    public AssemblyUnit saveAssemblyUnit(AssemblyUnit assemblyUnit) {
        return assemblyUnitRepository.save(assemblyUnit);
    }

    @Override
    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) throw new RuntimeException("User with ID " + id + " not found");
        return product.get();
    }
}

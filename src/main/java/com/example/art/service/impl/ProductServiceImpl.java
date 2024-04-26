package com.example.art.service.impl;

import com.example.art.controller.dto.AssemblyUnitDto;
import com.example.art.controller.dto.PartDto;
import com.example.art.exception.ProductAlreadyExistsException;
import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
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

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AssemblyUnitRepository assemblyUnitRepository;
    private final PartRepository partRepository;

//    @Override
//    public ProductDto saveProduct(ProductDto productDto) {
//        if (productRepository.findByDesignation(productDto.getDesignation()).isPresent())
//            throw new ProductAlreadyExistsException("Product already exists");
//
//        // Преобразование DTO в сущность Product
//        Product product = ProductMapper.toProductEntity(productDto);
//
//        // Сохранение Product
//        Product savedProduct = productRepository.save(product);
//
//        // Преобразование сущностей AssemblyUnitDto в сущности AssemblyUnit
//        List<AssemblyUnit> assemblyUnits = new ArrayList<>();
//        int lengthFor = (productDto.getAssembliesUnitsDto() == null) ? 0 : productDto.getAssembliesUnitsDto().size();
//        for (int i = 0; i < lengthFor; i++) {
//            AssemblyUnitDto assemblyUnitDto = productDto.getAssembliesUnitsDto().get(i);
//            AssemblyUnit assemblyUnit = AssemblyUnit.builder()
//                    .designation(assemblyUnitDto.getDesignation())
//                    .name(assemblyUnitDto.getName())
//                    .quantity(assemblyUnitDto.getQuantity())
//                    .level(assemblyUnitDto.getLevel())
//                    .product(savedProduct) // Устанавливаем связь с Product
//                    .build();
//
//            // Сохранение AssemblyUnit
//            AssemblyUnit savedAssemblyUnit = assemblyUnitRepository.save(assemblyUnit);
//
//            // Преобразование сущностей PartDto в сущности Part
//            List<Part> parts = new ArrayList<>();
//            int lengthForFor = (assemblyUnitDto.getPartsDto() == null) ? 0 : assemblyUnitDto.getPartsDto().size();
//            for (int j = 0; j < lengthForFor; j++) {
//                PartDto partDto = assemblyUnitDto.getPartsDto().get(j);
//                Part part = Part.builder()
//                        .designation(partDto.getDesignation())
//                        .name(partDto.getName())
//                        .quantity(partDto.getQuantity())
//                        .level(partDto.getLevel())
//                        .assemblyUnit(savedAssemblyUnit) // Устанавливаем связь с AssemblyUnit
//                        .build();
//
//                // Сохранение Part
//                parts.set(j, partRepository.save(part));
//            }
//            savedAssemblyUnit.setParts(parts); // Устанавливаем связанные Part в AssemblyUnit
//            assemblyUnits.set(i, savedAssemblyUnit); // Добавляем сохраненный AssemblyUnit в массив
//        }
//        savedProduct.setAssembliesUnits(assemblyUnits); // Устанавливаем связанные AssemblyUnit в Product
//
//        return ProductMapper.toProductDto(savedProduct);
//    }
    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        if (productRepository.findByDesignation(productDto.getDesignation()).isPresent())
            throw new ProductAlreadyExistsException("Product already exists");

        Product productSave = productRepository.save(ProductMapper.toProductEntity(productDto));
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

package com.example.art.service.impl;

import com.example.art.exception.ProductAlreadyExistsException;
import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.Product;
import com.example.art.exception.ProductNotFoundException;
import com.example.art.mapper.ProductMapper;
import com.example.art.repository.ProductRepository;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    public List<ProductDto> getByDesignation(String designation) {
        Optional<Product> product = productRepository.findByDesignation(designation);
        if(product.isEmpty())
            throw new ProductNotFoundException("Product with designation " + designation + " not found");
        List<Product> productList = productRepository.findAllByDesignation(designation);
        return productList.stream()
                .map(ProductMapper::toProductDto)
                .collect(Collectors.toList());
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
    public String[][] getForm(String designation,int versionDate) {
        Optional<Product> productOptional = productRepository.findByDesignation(designation);
        if(productOptional.isEmpty())
            throw new ProductNotFoundException("Product with designation " + designation + " not found");
        List<Product> productList = productRepository.findAllByDesignation(designation);
        productList.sort(Comparator.comparingInt(Product::getVersionDate));

        Product selectedProduct = getProduct(designation, versionDate, productList);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                
            }

        }

        return null;

//        Optional<Product> productOptional = productRepository.findByDesignation(designation);
//        if (productOptional.isEmpty()) {
//            throw new ProductNotFoundException("Product with designation " + designation + " not found");
//        }
//
//        List<Product> productList = productRepository.findAllByDesignation(designation);
//        productList.sort(Comparator.comparingInt(Product::getVersionDate));
//
//        Product selectedProduct = getProduct(designation, versionDate, productList);
//
//        List<String[]> form = new ArrayList<>();
//
//        for (AssemblyUnit assemblyUnit : selectedProduct.getAssembliesUnits()) {
//            form.add(new String[]{selectedProduct.getDesignation(), assemblyUnit.getDesignation(), assemblyUnit.getDesignation()});
//            for (Part part : assemblyUnit.getParts()) {
//                form.add(new String[]{selectedProduct.getDesignation(), assemblyUnit.getDesignation(), part.getDesignation()});
//            }
//        }
//
//        String[][] formArray = form.toArray(new String[0][]);
//        // Выводим результат на консоль
//        for (String[] row : formArray) {
//            System.out.println(Arrays.toString(row));
//        }
//
//        return formArray;
    }

}

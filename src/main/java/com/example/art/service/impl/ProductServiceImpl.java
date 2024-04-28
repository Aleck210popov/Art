package com.example.art.service.impl;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.exception.ProductAlreadyExistsException;
import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.Product;
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
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with designation " + designation + " not found");
        }

        List<Product> productList = productRepository.findAllByDesignation(designation);
        productList.sort(Comparator.comparingInt(Product::getVersionDate));

        Product selectedProduct = getProduct(designation, versionDate, productList);

        List<String[]> form = new ArrayList<>();

        Map<String, Integer> quantitiesMap = new HashMap<>();

        putQuantitiesMap(quantitiesMap, selectedProduct);
        processMap(quantitiesMap, selectedProduct, form);

//        quantitiesMap.forEach((key, value) -> System.out.println(key + " " + value));
//
        //        for (String[] row : formArray) {
//            System.out.println(Arrays.toString(row));
//        }

        return form.toArray(new String[0][]);
    }

    private void putQuantitiesMap(Map<String, Integer> quantitiesMap, Product selectedProduct) {
        quantitiesMap.put(selectedProduct.getDesignation(), selectedProduct.getQuantity());

        for (AssemblyUnit assemblyUnit : selectedProduct.getAssembliesUnits()) {

            if (quantitiesMap.containsKey(assemblyUnit.getDesignation())) {
                Integer fds = quantitiesMap.get(assemblyUnit.getDesignation());
                fds+=assemblyUnit.getQuantity() * selectedProduct.getQuantity();
                quantitiesMap.put(assemblyUnit.getDesignation(), fds);
            } else {
                quantitiesMap.put(assemblyUnit.getDesignation(), assemblyUnit.getQuantity()*selectedProduct.getQuantity());
            }
            for (Part part : assemblyUnit.getParts()) {
                if (quantitiesMap.containsKey(part.getDesignation())) {
                    Integer rrfds = quantitiesMap.get(part.getDesignation());
                    rrfds+=part.getQuantity()*assemblyUnit.getQuantity()*selectedProduct.getQuantity();
                    quantitiesMap.put(part.getDesignation(), rrfds);
                } else {
                    quantitiesMap.put(part.getDesignation(), part.getQuantity()*assemblyUnit.getQuantity()*selectedProduct.getQuantity());
                }
            }
        }
    }

    private void processMap(Map<String, Integer> quantitiesMap, Product selectedProduct, List<String[]> form) {
        form.add(new String[]{selectedProduct.getDesignation(),
                selectedProduct.getDesignation(),
                selectedProduct.getDesignation(),
                String.valueOf(selectedProduct.getLevel()),
                String.valueOf(selectedProduct.getQuantity()),
                String.valueOf(selectedProduct.getQuantity()),
                String.valueOf(selectedProduct.getQuantity())});


        for (AssemblyUnit assemblyUnit : selectedProduct.getAssembliesUnits()) {
            form.add(new String[]{selectedProduct.getDesignation(),
                    selectedProduct.getDesignation(),
                    assemblyUnit.getDesignation(),
                    String.valueOf(assemblyUnit.getLevel()),
                    String.valueOf(quantitiesMap.get(assemblyUnit.getDesignation())),
                    String.valueOf(assemblyUnit.getQuantity() * selectedProduct.getQuantity()),
                    String.valueOf(assemblyUnit.getQuantity())});
            for (Part part : assemblyUnit.getParts()) {
                form.add(new String[]{selectedProduct.getDesignation(),
                        assemblyUnit.getDesignation(),
                        part.getDesignation(),
                        String.valueOf(part.getLevel()),
                        String.valueOf(quantitiesMap.get(part.getDesignation())),
                        String.valueOf(part.getQuantity() * assemblyUnit.getQuantity()),
                        String.valueOf(part.getQuantity())});
            }
        }
    }

}

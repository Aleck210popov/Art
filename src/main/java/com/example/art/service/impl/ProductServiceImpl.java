package com.example.art.service.impl;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.repository.AssemblyUnitRepository;
import com.example.art.repository.PartRepository;
import com.example.art.repository.ProductRepository;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AssemblyUnitRepository assemblyUnitRepository;
    private final PartRepository partRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
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

package com.example.art.service.impl;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.repository.AssemblyUnitRepository;
import com.example.art.repository.PartRepository;
import com.example.art.repository.ProductRepository;
import com.example.art.service.StartProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartProgramServiceImpl implements StartProgramService {
    private final ProductRepository productRepository;
    private final AssemblyUnitRepository assemblyUnitRepository;
    private final PartRepository partRepository;
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveAssemblyUnit(AssemblyUnit assemblyUnit) {
        assemblyUnitRepository.save(assemblyUnit);
    }

    @Override
    public void savePart(Part part) {
        partRepository.save(part);
    }
}

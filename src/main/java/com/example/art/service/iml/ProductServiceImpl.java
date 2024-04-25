package com.example.art.service.iml;

import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import com.example.art.repository.AssemblyUnitRepository;
import com.example.art.repository.PartRepository;
import com.example.art.repository.ProductRepository;
import com.example.art.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AssemblyUnitRepository assemblyUnitRepository;
    private final PartRepository partRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void saveAssemblyUnit(AssemblyUnit assemblyUnit) {
        assemblyUnitRepository.save(assemblyUnit);
    }

    public void savePart(Part part) {
        partRepository.save(part);
    }
}

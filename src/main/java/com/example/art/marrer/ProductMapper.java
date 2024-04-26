package com.example.art.marrer;

import com.example.art.controller.dto.AssemblyUnitDto;
import com.example.art.controller.dto.PartDto;
import com.example.art.controller.dto.ProductDto;
import com.example.art.domain.AssemblyUnit;
import com.example.art.domain.Part;
import com.example.art.domain.Product;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProductMapper {
    public Product toProductEntity(ProductDto productDto) {

        Product product =  Product.builder()
                .designation(productDto.getDesignation())
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .level(productDto.getLevel())
                .assembliesUnits(toAssemblyUnit(productDto.getAssembliesUnitsDto()))
                .build();

        if (productDto.getId() != null) product.setId(productDto.getId());

        return product;
    }

    private List<AssemblyUnit> toAssemblyUnit(List<AssemblyUnitDto> assembliesUnitsDto) {
        List<AssemblyUnit> assemblyUnits = new ArrayList<>(assembliesUnitsDto.size());
        for (AssemblyUnitDto assemblyUnitDto : assembliesUnitsDto) {
            AssemblyUnit assemblyUnit = AssemblyUnit.builder()
                    .designation(assemblyUnitDto.getDesignation())
                    .name(assemblyUnitDto.getName())
                    .quantity(assemblyUnitDto.getQuantity())
                    .level(assemblyUnitDto.getLevel())
                    .parts(toParts(assemblyUnitDto.getPartsDto()))
                    .build();

            if (assemblyUnitDto.getId() != null) assemblyUnit.setId(assemblyUnitDto.getId());

            assemblyUnits.add(assemblyUnit);
        }
        return assemblyUnits;
    }

    private List<Part> toParts(List<PartDto> partsDto) {
        List<Part> parts = new ArrayList<>(partsDto.size());
        for (PartDto partDto : partsDto) {
            Part part = Part.builder()
                    .designation(partDto.getDesignation())
                    .name(partDto.getName())
                    .quantity(partDto.getQuantity())
                    .level(partDto.getLevel())
                    .build();

            if (partDto.getId() != null) part.setId(partDto.getId());

            parts.add(part);
        }
        return parts;
    }

    public ProductDto toProductDto(Product product) {

        return ProductDto.builder()
                .id(product.getId())
                .designation(product.getDesignation())
                .name(product.getName())
                .quantity(product.getQuantity())
                .level(product.getLevel())
                .assembliesUnitsDto(toAssemblyUnitDto(product.getAssembliesUnits()))
                .build();
    }

    private List<AssemblyUnitDto> toAssemblyUnitDto(List<AssemblyUnit> assembliesUnits) {
        List<AssemblyUnitDto> assembliesUnitsDto = new ArrayList<>(assembliesUnits.size());
        for (AssemblyUnit assemblyUnit : assembliesUnits) {
            AssemblyUnitDto assemblyUnitDto = AssemblyUnitDto.builder()
                    .id(assemblyUnit.getId())
                    .designation(assemblyUnit.getDesignation())
                    .name(assemblyUnit.getName())
                    .quantity(assemblyUnit.getQuantity())
                    .level(assemblyUnit.getLevel())
                    .partsDto(toPartsDto(assemblyUnit.getParts()))
                    .build();

            assembliesUnitsDto.add(assemblyUnitDto);
        }
        return assembliesUnitsDto;
    }

    private List<PartDto> toPartsDto(List<Part> parts) {
        List<PartDto> partsDto = new ArrayList<>(parts.size());
        for (Part part : parts) {
            PartDto partDto = PartDto.builder()
                    .id(part.getId())
                    .designation(part.getDesignation())
                    .name(part.getName())
                    .quantity(part.getQuantity())
                    .level(part.getLevel())
                    .build();
            partsDto.add(partDto);
        }
        return partsDto;
    }
}

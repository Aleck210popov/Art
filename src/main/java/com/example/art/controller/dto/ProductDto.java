package com.example.art.controller.dto;

import com.example.art.domain.AssemblyUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String designation;

    private String name;

    private int quantity;

    private int level;

    private int versionDate;

    private List<AssemblyUnitDto> assembliesUnitsDto;
}

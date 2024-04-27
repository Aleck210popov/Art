package com.example.art.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyUnitDto {
    private Long id;

    private String designation;

    private String name;

    private int quantity;

    private int level;

    private int versionDate;

    private List<PartDto> partsDto;
}

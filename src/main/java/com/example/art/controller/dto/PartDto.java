package com.example.art.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {
    private Long id;

    private String designation;

    private String name;

    private int quantity;

    private int level;

    private int versionDate;
}

package com.example.art.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assembly_unit")
public class AssemblyUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column (name = "designation")
    private String designation;

    @Column (name = "name")
    private String name;

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "level")
    private int level;

    @Column (name = "version_date")
    private int versionDate;

    @ManyToOne
    private Product product;

    @OneToMany(mappedBy = "assemblyUnit", cascade = CascadeType.ALL)
    private List<Part> parts;
}

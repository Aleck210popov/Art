package com.example.art.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "part")
public class Part {
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
    private AssemblyUnit assemblyUnit;
}

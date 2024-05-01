//package com.example.art.demo;
//
//import com.example.art.domain.AssemblyUnit;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "pproduct")
//public class Pproduct {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column (name = "designation")
//    private String designation;
//
//    @Column (name = "name")
//    private String name;
//
//    @Column (name = "quantity")
//    private int quantity;
//
//    @Column (name = "level")
//    private int level;
//
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<AssemblyUnit> assembliesUnits;
//}
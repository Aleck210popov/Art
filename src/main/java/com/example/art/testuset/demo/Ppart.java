//package com.example.art.testuset.demo;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "ppart")
//public class Ppart {
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
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Pproduct pproduct;
//}
package com.example.art.repository;

import com.example.art.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDesignation(String designation);
    Optional<Product> findByVersionDate(int versionDate);
    List<Product> findAllByDesignation(String designation);
}

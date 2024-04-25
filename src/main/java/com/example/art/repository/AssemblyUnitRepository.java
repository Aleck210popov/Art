package com.example.art.repository;

import com.example.art.domain.AssemblyUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssemblyUnitRepository extends JpaRepository<AssemblyUnit, Long> {
}

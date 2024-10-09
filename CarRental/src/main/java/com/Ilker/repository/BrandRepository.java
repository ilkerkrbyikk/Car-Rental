package com.Ilker.repository;

import com.Ilker.entitiy.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {

    Brand getBrandById(Long id);
    boolean existsByName(String name);
    boolean existsById(Long id);
}

package com.Ilker.repository;

import com.Ilker.entitiy.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Long> {

    Color getColorById(Long colorId);
    boolean existsByName(String name);
    boolean existsById(Long id);
}

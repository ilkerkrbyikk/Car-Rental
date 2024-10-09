package com.Ilker.repository;

import com.Ilker.entitiy.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Long> {

    Model getModelById(Long id);
    boolean existsByName(String name);
    boolean existsById(Long id);

}

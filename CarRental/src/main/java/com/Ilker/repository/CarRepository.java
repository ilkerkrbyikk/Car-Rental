package com.Ilker.repository;

import com.Ilker.entitiy.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {

    boolean existsById(Long id);

    Car getCarById(Long carId);
    Car findCarById(Long carId);

    List<Car> findByAvailable(boolean available);
}

package com.Ilker.repository;

import com.Ilker.entitiy.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository<Fuel, Long> {

   Fuel getFuelById(Long fuelId);
}

package com.Ilker.repository;

import com.Ilker.entitiy.Gear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearRepository extends JpaRepository<Gear,Long> {

    Gear getGearById(Long gearId);
}

package com.Ilker.service.fuel;

import com.Ilker.entitiy.Fuel;
import com.Ilker.request.AddFuelRequest;

import java.util.List;

public interface FuelService {
    List<Fuel> getAllFuel();
    Fuel addFuel(AddFuelRequest request);
    void deleteFuel(Long fuelId);
}

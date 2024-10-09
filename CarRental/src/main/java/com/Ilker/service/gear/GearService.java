package com.Ilker.service.gear;

import com.Ilker.entitiy.Gear;
import com.Ilker.request.AddGearRequest;

import java.util.List;

public interface GearService {

    Gear add(AddGearRequest request);
    void deleteGear(Long gearId);
    List<Gear> getAll();
}

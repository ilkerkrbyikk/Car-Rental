package com.Ilker.service.gear;

import com.Ilker.entitiy.Gear;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.GearRepository;
import com.Ilker.request.AddCarRequest;
import com.Ilker.request.AddGearRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GearServiceImpl implements GearService {

    private final GearRepository gearRepository;


    @Override
    public List<Gear> getAll(){
        return gearRepository.findAll();
    }

    @Override
    public Gear add(AddGearRequest request){
        Gear newGear = new Gear();
        newGear.setName(request.getName());
        return gearRepository.save(newGear);
    }

    @Override
    public void deleteGear(Long gearId){
        gearRepository.deleteById(gearId);
    }


}

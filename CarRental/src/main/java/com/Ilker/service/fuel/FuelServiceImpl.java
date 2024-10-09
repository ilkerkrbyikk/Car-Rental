package com.Ilker.service.fuel;

import com.Ilker.entitiy.Fuel;
import com.Ilker.repository.FuelRepository;
import com.Ilker.request.AddFuelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;


    @Override
    public List<Fuel> getAllFuel() {
        return fuelRepository.findAll();
    }

    @Override
    public Fuel addFuel(AddFuelRequest request) {
        // Zaten max 2-3 çeşit olacak diye aynı isimde var mı diye kontrol etmeye gerek duymadım.
        Fuel fuel = new Fuel();
        fuel.setName(request.getName());
        return fuelRepository.save(fuel);
    }

    @Override
    public void deleteFuel(Long fuelId) {
        // Buna da gerek yoktu adettendir diye yazıcam.
        Fuel fuel = fuelRepository.findById(fuelId).orElse(null);
        fuelRepository.deleteById(fuelId);
    }
}

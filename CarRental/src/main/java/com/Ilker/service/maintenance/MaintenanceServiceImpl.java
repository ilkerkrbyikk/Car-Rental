package com.Ilker.service.maintenance;

import com.Ilker.entitiy.Car;
import com.Ilker.entitiy.Maintenance;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.CarRepository;
import com.Ilker.repository.MaintenanceRepository;
import com.Ilker.request.AddMaintenanceRequest;
import com.Ilker.request.UpdateMaintenanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService{

    private final MaintenanceRepository maintenanceRepository;
    private final CarRepository carRepository;
    @Override
    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance add(AddMaintenanceRequest request) {
        Car car = carRepository.findCarById(request.getCarId());

        Maintenance maintenance = new Maintenance();
        maintenance.setCar(car);
        maintenance.setDescription(request.getDescription());
        maintenance.setReturnDate(request.getReturnDate());
        car.setAvailable(false);
        /* TODO: ARABAYI FALSE ÇEKTIM AMA RETURNDATE DE TRUE YAPILACAK.
          * ONUN KODUNUDA YAZ BURAYA.
        */

        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance update(UpdateMaintenanceRequest request, Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Maintenance not found."));
        Car car = carRepository.findCarById(id);
        maintenance.setReturnDate(request.getReturnDate());
        maintenance.setCar(car);
        maintenance.setDescription(request.getDescription());
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void delete(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Maintenance not found."));
        maintenanceRepository.deleteById(id);
    }
}

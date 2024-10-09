package com.Ilker.service.car_crash;

import com.Ilker.entitiy.Car;
import com.Ilker.entitiy.CarCrash;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.CarCrashRepository;
import com.Ilker.repository.CarRepository;
import com.Ilker.request.CreateCarCrashRequest;
import com.Ilker.request.UpdateCarCrashRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarCrashServiceImpl implements CarCrashService{

    private final CarCrashRepository carCrashRepository;
    private final CarRepository carRepository;
    @Override
    public List<CarCrash> getAll() {
        return carCrashRepository.findAll();
    }

    @Override
    public CarCrash add(CreateCarCrashRequest request) {
        Car car = carRepository.findCarById(request.getCarId());

        CarCrash crash = new CarCrash();
        crash.setCar(car);
        crash.setValuation(request.getValuation());
        crash.setLocalDate(request.getLocalDate());

        return carCrashRepository.save(crash);
    }

    @Override
    public CarCrash update(UpdateCarCrashRequest request, Long id) {
        checkIsCarCrashExistsById(id);
        CarCrash carCrash = carCrashRepository.findById(id).orElseThrow();
        Car car = carRepository.getCarById(request.getCarId());

        carCrash.setCar(car);
        carCrash.setValuation(request.getValuation());
        carCrash.setLocalDate(request.getLocalDate());

        return carCrashRepository.save(carCrash);
    }

    @Override
    public void deleteCrash(Long id) {
        checkIsCarCrashExistsById(id);
        carCrashRepository.deleteById(id);
    }

    public void checkIsCarCrashExistsById(Long id){
        if(!carCrashRepository.existsById(id)){
            throw new ResourceNotFoundException("Doesn't exists.");
        }
    }
}

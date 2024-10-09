package com.Ilker.service.car;

import com.Ilker.entitiy.Car;
import com.Ilker.request.AddCarRequest;
import com.Ilker.request.UpdateCarRequest;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();
    Car add(AddCarRequest request);
    Car update(UpdateCarRequest request, Long carId);
    void delete(Long id);
    void checkIsExistsByCarId(Long carId);
    void updateAvailable(Long carId, boolean available);
    List<Car> getAvailableCars();

}

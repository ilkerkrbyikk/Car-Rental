package com.Ilker.service.car_crash;

import com.Ilker.entitiy.CarCrash;
import com.Ilker.request.CreateCarCrashRequest;
import com.Ilker.request.UpdateCarCrashRequest;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CarCrashService {

    List<CarCrash> getAll();
    CarCrash add(CreateCarCrashRequest request);
    CarCrash update(UpdateCarCrashRequest request, Long id);
    void deleteCrash(Long id);
}

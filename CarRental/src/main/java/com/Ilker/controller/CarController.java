package com.Ilker.controller;

import com.Ilker.entitiy.Car;
import com.Ilker.request.AddCarRequest;
import com.Ilker.request.UpdateCarRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/admin/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCars(){
        List<Car> carList = carService.getAllCars();
        return ResponseEntity.ok(new ApiResponse("Cars found successfully.", carList));
    }

    @GetMapping("/available/all")
    public ResponseEntity<ApiResponse> getAvailableCars(){
        List<Car> carList = carService.getAvailableCars();
        return ResponseEntity.ok(new ApiResponse("Available cars found successfully.",  carList));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCar(@RequestBody AddCarRequest request){
        try {
            Car car = carService.add(request);
            return ResponseEntity.ok(new ApiResponse("Car added successfully.", car));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{carId}/update")
    public ResponseEntity<ApiResponse> updateCar(@RequestBody UpdateCarRequest request, @PathVariable Long carId){
        try {
            Car updatedCar = carService.update(request,carId);
            return ResponseEntity.ok(new ApiResponse("Car updated successfully.", updatedCar ));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{carId}/delete")
    public ResponseEntity<ApiResponse> deleteCar(@PathVariable Long carId){
        try {
            carService.delete(carId);
            return ResponseEntity.ok(new ApiResponse("Car deleted successfully.",null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }
}

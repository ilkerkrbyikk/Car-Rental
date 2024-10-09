package com.Ilker.controller;

import com.Ilker.entitiy.Car;
import com.Ilker.entitiy.Fuel;
import com.Ilker.request.AddCarRequest;
import com.Ilker.request.AddFuelRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.fuel.FuelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/admin/fuel")
@RequiredArgsConstructor
public class FuelController {

    private final FuelService fuelService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCars(){
        List<Fuel> fuelList = fuelService.getAllFuel();
        return ResponseEntity.ok(new ApiResponse("Fuels founded successfully.", fuelList));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCar(@RequestBody AddFuelRequest request){
        try {
            Fuel fuel = fuelService.addFuel(request);
            return ResponseEntity.ok(new ApiResponse("Fuel added successfully.", fuel));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{fuelId}/delete")
    public ResponseEntity<ApiResponse> deleteCar(@PathVariable Long fuelId){
        try {
            fuelService.deleteFuel(fuelId);
            return ResponseEntity.ok(new ApiResponse("Fuel deleted successfully.",null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }
}

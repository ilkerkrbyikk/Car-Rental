package com.Ilker.controller;

import com.Ilker.entitiy.Car;
import com.Ilker.entitiy.CarCrash;
import com.Ilker.request.CreateCarCrashRequest;
import com.Ilker.request.UpdateCarCrashRequest;
import com.Ilker.request.UpdateCarRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.car_crash.CarCrashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/crash")
@RequiredArgsConstructor
public class CrashController {

    private final CarCrashService carCrashService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<CarCrash> crashes = carCrashService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success",crashes));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody CreateCarCrashRequest request){
        try {
            CarCrash crash = carCrashService.add(request);
            return ResponseEntity.ok(new ApiResponse("Crash added successfully.", crash));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateCarCrashRequest request, @PathVariable Long id){
        try {
            CarCrash crash = carCrashService.update(request, id);
            return ResponseEntity.ok(new ApiResponse("Updated successfully.", crash));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        carCrashService.deleteCrash(id);
        return ResponseEntity.ok(new ApiResponse("Deleted successfully.",null));
    }
}

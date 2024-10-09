package com.Ilker.controller;

import com.Ilker.entitiy.Color;
import com.Ilker.entitiy.Gear;
import com.Ilker.exceptions.ColorAlreadyExistsException;
import com.Ilker.exceptions.GearAlreadyExistsException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.request.AddColorRequest;
import com.Ilker.request.AddGearRequest;
import com.Ilker.request.UpdateColorRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.gear.GearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/admin/gear")
@RequiredArgsConstructor
public class GearController {

    private final GearService gearService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllColors(){
        List<Gear> colorList = gearService.getAll();
        return ResponseEntity.ok(new ApiResponse("Gear found success.", colorList));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addColor(@RequestBody AddGearRequest request){
        try {
            Gear gear = gearService.add(request);
            return ResponseEntity.ok(new ApiResponse("Gear added successfully.", gear));
        } catch (GearAlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable Long id){
        gearService.deleteGear(id);
        ResponseEntity.ok(new ApiResponse("Deleted successfully.", null));
    }
}

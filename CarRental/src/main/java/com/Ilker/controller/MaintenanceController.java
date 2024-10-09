package com.Ilker.controller;

import com.Ilker.entitiy.Maintenance;
import com.Ilker.request.AddMaintenanceRequest;
import com.Ilker.request.UpdateMaintenanceRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.maintenance.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/admin/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<Maintenance> maintenances= maintenanceService.getAll();
        return ResponseEntity.ok(new ApiResponse("Success.",maintenances));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AddMaintenanceRequest request){
        try{
            Maintenance maintenance = maintenanceService.add(request);
            return ResponseEntity.ok(new ApiResponse("Maintenance added successfully.", maintenance));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateMaintenanceRequest request,
                                              @PathVariable Long id){
        try {
            Maintenance maintenance = maintenanceService.update(request,id);
            return ResponseEntity.ok(new ApiResponse("Updated successfully.",maintenance));

        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        maintenanceService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Deleted successfully.",null));
    }
}

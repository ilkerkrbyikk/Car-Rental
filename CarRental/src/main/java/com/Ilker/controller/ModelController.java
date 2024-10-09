package com.Ilker.controller;

import com.Ilker.entitiy.Model;
import com.Ilker.exceptions.ModelAlreadyExistException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.request.AddModelRequest;
import com.Ilker.request.UpdateModelRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.brand_model.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/admin/model")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllModels(){
        try {
            List<Model> modelList = modelService.getAll();
            return ResponseEntity.ok(new ApiResponse("Success.", modelList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addModel(@RequestBody AddModelRequest request){
        try {
            Model model = modelService.add(request);
            return ResponseEntity.ok(new ApiResponse("Model added successfully.", model));
        } catch (ModelAlreadyExistException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateModel(@RequestBody UpdateModelRequest request, @PathVariable Long id){
        try {
            Model updatedBrand = modelService.update(request,id);
            return ResponseEntity.ok(new ApiResponse("Model uptaded successfully.", updatedBrand));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteModel(@PathVariable Long id){
        try {
            modelService.deleteModel(id);
            return ResponseEntity.ok(new ApiResponse("Model deleted successfully.",null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}

package com.Ilker.controller;

import com.Ilker.entitiy.Brand;
import com.Ilker.exceptions.BrandAlreadyExistsException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.request.AddBrandRequest;
import com.Ilker.request.UpdateBrandRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {


    private final BrandService brandService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllBrands(){
        try {
            List<Brand> brandList = brandService.getAllBrands();
            return ResponseEntity.ok(new ApiResponse("Successfully found.", brandList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBrand(@RequestBody AddBrandRequest request){
        try {
            Brand newBrand = brandService.add(request);
            return ResponseEntity.ok(new ApiResponse("Brand added successfully", newBrand));
        } catch (BrandAlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest
            ,@PathVariable Long id){
        try {
            Brand updatedBrand = brandService.update(updateBrandRequest,id);
            return ResponseEntity.ok(new ApiResponse("Brand updated successfully", updatedBrand));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteBrand(@PathVariable Long id){
        try {
            brandService.delete(id);
            return ResponseEntity.ok(new ApiResponse("Brand deleted successfully.",null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }


}

package com.Ilker.controller;

import com.Ilker.entitiy.Color;
import com.Ilker.exceptions.ColorAlreadyExistsException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.request.AddColorRequest;
import com.Ilker.request.UpdateColorRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.colors.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/admin/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllColors(){
        List<Color> colorList = colorService.getAll();
        return ResponseEntity.ok(new ApiResponse("Colors found success.", colorList));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addColor(@RequestBody AddColorRequest request){
        try {
            Color color = colorService.add(request);
            return ResponseEntity.ok(new ApiResponse("Color added successfully.", color));
        } catch (ColorAlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}/update")
    public ResponseEntity<ApiResponse> updateColor(@RequestBody UpdateColorRequest request, @PathVariable Long id){
        try {
            Color color = colorService.update(request, id);
            return ResponseEntity.ok(new ApiResponse("Color updated successfully.", color));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}/delete")
    public void delete(@PathVariable Long id){
        colorService.delete(id);
        ResponseEntity.ok(new ApiResponse("Deleted successfully.", null));
    }
}

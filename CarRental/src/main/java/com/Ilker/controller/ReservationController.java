package com.Ilker.controller;

import com.Ilker.entitiy.Reservation;
import com.Ilker.exceptions.CarIsNotAvailableException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.request.MakeReservationRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllReservations(){
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            return ResponseEntity.ok(new ApiResponse("Success", reservations));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/makereservation")
    public ResponseEntity<ApiResponse> makeReservation(@RequestBody MakeReservationRequest makeReservationRequest){
        try {
            Reservation reservation = reservationService.makeReservation(makeReservationRequest);
            return ResponseEntity.ok(new ApiResponse("Reserved successfully.", makeReservationRequest));
        } catch (CarIsNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Try again.",null));
        }
    }


    @DeleteMapping("/{reservationId}/delete")
    public ResponseEntity<ApiResponse> cancelReservation(@PathVariable Long reservationId){
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok(new ApiResponse("Canceled successfully.",null));
    }


}

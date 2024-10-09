package com.Ilker.service.reservation;

import com.Ilker.entitiy.Reservation;
import com.Ilker.request.MakeReservationRequest;

import java.util.List;

public interface ReservationService {
    void checkIsExistsByReservationId(Long reservationId);
    void cancelReservation(Long reservationId);
    Reservation makeReservation(MakeReservationRequest request);
    List<Reservation> getAllReservations();
}

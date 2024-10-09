package com.Ilker.repository;

import com.Ilker.entitiy.Car;
import com.Ilker.entitiy.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    //List<Reservation> findByReservationDate(LocalDate localDate);
    boolean existsByReservationId(Long reservationId);
    List<Reservation> findByCarAndStartDateBetween(Car car, LocalDate startDateStart, LocalDate startDateEnd);
    List<Reservation> findByCarAndEndDateBetween(Car car, LocalDate endDateStart, LocalDate endDateEnd);


}

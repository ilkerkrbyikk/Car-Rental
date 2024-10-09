package com.Ilker.service.reservation;

import com.Ilker.entitiy.Car;
import com.Ilker.entitiy.Reservation;
import com.Ilker.entitiy.User;
import com.Ilker.enums.ReservationStatus;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.CarRepository;
import com.Ilker.repository.ReservationRepository;
import com.Ilker.repository.UserRepository;
import com.Ilker.request.MakeReservationRequest;
import com.Ilker.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CarService carService;
    private final UserRepository userRepository;

    @Override
    public List<Reservation> getAllReservations(){

        return reservationRepository.findAll();
    }

    @Override
    public Reservation makeReservation(MakeReservationRequest request){
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(()-> new ResourceNotFoundException("Car not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new UsernameNotFoundException("CUSTOMER NOT FOUND."));

      List<Reservation> overLappingStartDate = reservationRepository.findByCarAndStartDateBetween(
              car, request.getStartDate(), request.getEndDate());

        List<Reservation> overlappingEndDate = reservationRepository.findByCarAndEndDateBetween(
                car, request.getStartDate(), request.getEndDate());


        if(!overLappingStartDate.isEmpty() || !overlappingEndDate.isEmpty()){
            //TODO: ADAM AKILLI EXCEPTION YAZ.
            throw new ResourceNotFoundException("Car is reserved for seleted dates.");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setCar(car);
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setStatus(ReservationStatus.PENDING);

        Long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        reservation.setTotalAmount(car.getDailyPrice()*days);

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId){
        checkIsExistsByReservationId(reservationId);
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        if(reservation!= null){
            carService.updateAvailable(reservation.getCar().getId(), true);
            reservationRepository.delete(reservation);
        }

    }

    @Override
    public void checkIsExistsByReservationId(Long reservationId){
        if(!reservationRepository.existsByReservationId(reservationId)){
            throw new ResourceNotFoundException("Reservation is not found.");
        }
    }
}

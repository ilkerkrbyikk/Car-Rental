package com.Ilker.entitiy;

import com.Ilker.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name= "users_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    private double totalAmount;
}

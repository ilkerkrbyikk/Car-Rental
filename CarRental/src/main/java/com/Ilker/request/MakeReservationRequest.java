package com.Ilker.request;

import com.Ilker.entitiy.Car;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeReservationRequest {

    private Long carId;

    private Long userId;

    private LocalDate startDate;

    private LocalDate endDate;
}


package com.Ilker.request;

import com.Ilker.entitiy.Car;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCrashRequest {

    private LocalDate localDate;

    private double valuation;

    private Long carId;

}

package com.Ilker.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarCrashRequest {


    private LocalDate localDate;

    private double valuation;

    private Long carId;


}

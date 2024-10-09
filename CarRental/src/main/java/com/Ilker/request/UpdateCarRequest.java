package com.Ilker.request;

import com.Ilker.entitiy.Brand;
import com.Ilker.entitiy.Model;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {

    private Long id;
    private int modelYear;
    private Long gearId;
    private Long fuelId;
    private double dailyPrice;
    private Long colorId;
    private boolean isAvailable;
    private int kilometer;


    private Long brandId;

    private Long modelId;
}

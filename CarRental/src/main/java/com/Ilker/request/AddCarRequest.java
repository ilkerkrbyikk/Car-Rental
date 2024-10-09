package com.Ilker.request;

import com.Ilker.entitiy.Brand;
import com.Ilker.entitiy.Model;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    private int modelYear;
    private Long gearId;
    private Long fuelId;
    private double dailyPrice;
    private boolean isAvailable;
    private int kilometer;

    private Long colorId;

    private Long brandId;

    private Long modelId;
}

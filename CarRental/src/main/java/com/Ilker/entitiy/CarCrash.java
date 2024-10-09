package com.Ilker.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CarCrash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crash_date", nullable = false)
    private LocalDate localDate;

    private double valuation;

    @ManyToOne()
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;


}

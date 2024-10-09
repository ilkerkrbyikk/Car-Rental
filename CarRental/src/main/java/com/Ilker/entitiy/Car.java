package com.Ilker.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int modelYear;
    private double dailyPrice;


    private boolean available = true;
    private int kilometer;

    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(name = "gear_id")
    private Gear gear;


    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;


    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "car")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "car")
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<CarCrash> crashes = new ArrayList<>();

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Maintenance> maintenances;


}

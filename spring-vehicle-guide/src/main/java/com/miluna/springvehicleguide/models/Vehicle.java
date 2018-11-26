package com.miluna.springvehicleguide.models;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Vehicle {

    private Long id;

    private Brand brand;

    private List<Engine> engines;

    private String name;

    private String description;

    private Integer year;

    private Integer weight;

    private Integer doors;

    private Character segment;

    private Long basePrice;

    public Vehicle(){}

    public Vehicle(VehicleEntity entity){
        this.id = entity.getId();
        this.brand = (entity.getBrand() != null) ? new Brand(entity.getBrand()) : new Brand();
        this.engines = (entity.getEngines() != null) ?
                entity.getEngines()
                        .stream()
                        .map(e -> new Engine(e))
                        .collect(Collectors.toList())
                : new ArrayList<>();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.year = entity.getYear();
        this.weight = entity.getWeight();
        this.doors = entity.getDoors();
        this.segment = entity.getSegment();
        this.basePrice = entity.getBasePrice();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

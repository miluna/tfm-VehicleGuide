package com.miluna.springvehicleguide.models;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private Long id;

    private Brand brand;

    private List<Engine> engines;

    private String name;

    private String description;

    private Date year;

    private Number weight;

    private Number doors;

    private Character segment;

    private Number basePrice;

    public Vehicle(VehicleEntity entity){
        this.id = entity.getId();
        this.brand = new Brand(entity.getBrand());
        this.engines = entity.getEngines()
                        .stream()
                        .map(e -> new Engine(e))
                        .collect(Collectors.toList());
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

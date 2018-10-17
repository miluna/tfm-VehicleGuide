package com.miluna.springvehicleguide.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

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

}

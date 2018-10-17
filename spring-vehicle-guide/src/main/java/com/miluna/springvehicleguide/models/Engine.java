package com.miluna.springvehicleguide.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engine {

    private Long id;

    private Number cylinders;

    private Number displacement;

    private Number horsepower;

    private Boolean hasTurbo;

    private String type;

    private Number pollution;

    private Character energyCertificate;

    private Number autonomy;

}

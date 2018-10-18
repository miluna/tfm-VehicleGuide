package com.miluna.springvehicleguide.models;

import com.miluna.springvehicleguide.entities.EngineEntity;
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

    public Engine(EngineEntity entity){
        this.id = entity.getId();
        this.cylinders = entity.getCylinders();
        this.displacement = entity.getDisplacement();
        this.horsepower = entity.getHorsepower();
        this.hasTurbo = entity.getHasTurbo();
        this.type = entity.getType();
        this.pollution = entity.getPollution();
        this.energyCertificate = entity.getEnergyCertificate();
        this.autonomy = entity.getAutonomy();
    }

}

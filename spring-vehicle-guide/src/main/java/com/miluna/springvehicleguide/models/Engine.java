package com.miluna.springvehicleguide.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.miluna.springvehicleguide.entities.EngineEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Engine {

    private Long id;

    private Integer cylinders;

    private Integer displacement;

    private Integer horsepower;

    private Boolean hasTurbo;

    private String type;

    private Integer pollution;

    private Character energyCertificate;

    private Integer autonomy;

    public Engine(){}

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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}

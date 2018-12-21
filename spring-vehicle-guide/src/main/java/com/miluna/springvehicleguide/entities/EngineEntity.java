package com.miluna.springvehicleguide.entities;

import com.google.gson.Gson;
import com.miluna.springvehicleguide.models.Engine;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "engines")
public class EngineEntity implements UpdateableEntity<EngineEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cylinders")
    private Integer cylinders;

    @Column(name = "displacement")
    private Integer displacement;

    @Column(name = "horsepower")
    private Integer horsepower;

    @Column(name = "hasTurbo")
    private Boolean hasTurbo;

    @Column(name = "type")
    private String type;

    @Column(name = "pollution")
    private Integer pollution;

    @Column(name = "energyCertificate")
    private Character energyCertificate;

    @Column(name = "autonomy")
    private Integer autonomy;

    public EngineEntity(){}

    public EngineEntity(Engine engine){
        setId(engine.getId());
        setCylinders(engine.getCylinders());
        setDisplacement(engine.getDisplacement());
        setHorsepower(engine.getHorsepower());
        setHasTurbo(engine.getHasTurbo());
        setType(engine.getType());
        setPollution(engine.getPollution());
        setEnergyCertificate(engine.getEnergyCertificate());
        setAutonomy(engine.getAutonomy());
    }

    // VALIDATION

    public void setCylinders(Integer cylinders){
        if (cylinders == null || cylinders < 0) this.cylinders = 0;
        else this.cylinders = cylinders;
    }

    public void setDisplacement(Integer displacement){
        if (displacement == null || displacement < 0) this.displacement = 0;
        else this.displacement = displacement;
    }

    public void setHorsepower(Integer horsepower){
        if (horsepower == null || horsepower < 0) this.horsepower = 0;
        else this.horsepower = horsepower;
    }

    public void setAutonomy(Integer autonomy){
        if (autonomy == null || autonomy < 0) this.autonomy = 0;
        else this.autonomy = autonomy;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public void updateProperties(EngineEntity target) {

        this.setCylinders(target.getCylinders());
        this.setDisplacement(target.getDisplacement());
        this.setHorsepower(target.getHorsepower());
        this.setAutonomy(target.getAutonomy());
        this.hasTurbo = target.getHasTurbo();
        this.type = target.getType();
        this.pollution = target.getPollution();
        this.energyCertificate = target.getEnergyCertificate();
    }
}

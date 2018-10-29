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
public class EngineEntity implements UpdateableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "cylinders")
    private Number cylinders;

    @Column(name = "displacement")
    private Number displacement;

    @Column(name = "horsepower")
    private Number horsepower;

    @Column(name = "hasTurbo")
    private Boolean hasTurbo;

    @Column(name = "type")
    private String type;

    @Column(name = "pollution")
    private Number pollution;

    @Column(name = "energyCertificate")
    private Character energyCertificate;

    @Column(name = "autonomy")
    private Number autonomy;

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

    public void setCylinders(Number cylinders){
        if (cylinders.longValue() < 0) this.cylinders = 0;
        else this.cylinders = cylinders.longValue();
    }

    public void setDisplacement(Number displacement){
        if (displacement.longValue() < 0) this.displacement = 0;
        else this.displacement = displacement.longValue();
    }

    public void setHorsepower(Number horsepower){
        if (horsepower.longValue() < 0) this.horsepower = 0;
        else this.horsepower = horsepower.longValue();
    }

    public void setAutonomy(Number autonomy){
        if (autonomy.longValue() < 0) this.autonomy = 0;
        else this.autonomy = autonomy.longValue();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public void updateProperties(Object newEntity) {
        EngineEntity target = (EngineEntity) newEntity;

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
